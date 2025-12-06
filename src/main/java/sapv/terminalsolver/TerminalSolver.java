package sapv.terminalsolver;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.ClickHistoryTerminalState;
import sapv.terminalsolver.terminal.TerminalState;
import sapv.terminalsolver.terminal.TerminalType;

import java.util.Map;

public class TerminalSolver {
    public static final TerminalSolver INSTANCE = new TerminalSolver();
    public static void init() {
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client, world) -> INSTANCE.reset());
    }
    private TerminalSolver() {}

    private boolean toggled = true;

    private TerminalState currentTerminalState;
    @NotNull
    private Click[] cachedSolution = Click.EMPTY_SOLUTION;
    private boolean updateSolution;

    private int lastClickSyncId = -10;

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        reset();
    }

    public boolean isToggled() {
        return toggled;
    }

    private void reset() {
        currentTerminalState = null;
        cachedSolution = Click.EMPTY_SOLUTION;
        lastClickSyncId = -10;
    }

    void onPostScreenRender(DrawContext context, Screen screen, float deltaTicks) {
        if (!toggled) return;
        if (currentTerminalState == null) return;
        currentTerminalState.type().renderer.render(cachedSolution, context, screen, deltaTicks);
    }

    void onSendPacket(Packet<?> packet) {
        switch (packet) {
            case ClickSlotC2SPacket p -> onClickSlotPacket(p);
            case CloseHandledScreenC2SPacket p -> currentTerminalState = null;
            default -> {}
        }
    }

    void onMainReceivePacket(Packet<?> packet) {
        switch (packet) {
            case OpenScreenS2CPacket p -> onOpenScreenPacket(p);
            case ScreenHandlerSlotUpdateS2CPacket p -> onSlotUpdate(p);
            case CloseScreenS2CPacket p -> currentTerminalState = null;
            default -> {}
        }
    }

    void onPostRunTasks() {
        if (updateSolution) {
            updateSolution = false;
            if (currentTerminalState != null) {
                cachedSolution = currentTerminalState.getSolutions();
            }
        }
    }

    private void onClickSlotPacket(ClickSlotC2SPacket packet) {
        if (currentTerminalState instanceof ClickHistoryTerminalState historyState) {
            int syncId = packet.syncId();
            if (lastClickSyncId != syncId && !historyState.hasClick(packet.slot())) {
                lastClickSyncId = syncId;
                historyState.addClick(packet.slot());
            }
        }
    }

    private void onOpenScreenPacket(OpenScreenS2CPacket packet) {
        TerminalState before = currentTerminalState;
        currentTerminalState = null;
        if (!toggled) return;
        int size = getSlotSize(packet.getScreenHandlerType());
        if (size == 0) return;
        TerminalType type = TerminalType.get(packet.getName().getString(), size);
        if (type == null) return;
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(size, ItemStack.EMPTY);
        Text title = packet.getName();
        int syncId = packet.getSyncId();
        if (before instanceof ClickHistoryTerminalState beforeHistoryState &&
                beforeHistoryState.type() == TerminalType.STARTS_WITH &&
                type == TerminalType.STARTS_WITH &&
                beforeHistoryState.title().equals(title) &&
                beforeHistoryState.syncId() % 100 + 1 == syncId
        ) {
            ClickHistoryTerminalState clickHistoryTerminalState = new ClickHistoryTerminalState(type, title, syncId, stacks);
            beforeHistoryState.forEach(clickHistoryTerminalState::addClick);
            currentTerminalState = clickHistoryTerminalState;
        } else if (type == TerminalType.STARTS_WITH) {
            currentTerminalState = new ClickHistoryTerminalState(type, title, syncId, stacks);
        } else {
            currentTerminalState = new TerminalState(type, title, syncId, stacks);
        }
    }


    private void onSlotUpdate(ScreenHandlerSlotUpdateS2CPacket packet) {
        if (!toggled) return;
        if (currentTerminalState == null) return;
        if (currentTerminalState.syncId() != packet.getSyncId()) return;
        int slot = packet.getSlot();
        DefaultedList<ItemStack> stacks = currentTerminalState.stacks();
        if (slot < 0 || slot >= stacks.size()) return;
        stacks.set(packet.getSlot(), packet.getStack().copy());
        updateSolution = true;
    }

    private static final Map<ScreenHandlerType<?>, Integer> slotSizeMap = Map.of(
            ScreenHandlerType.GENERIC_9X1, 9,
            ScreenHandlerType.GENERIC_9X2, 18,
            ScreenHandlerType.GENERIC_9X3, 27,
            ScreenHandlerType.GENERIC_9X4, 36,
            ScreenHandlerType.GENERIC_9X5, 45,
            ScreenHandlerType.GENERIC_9X6, 54
    );

    private static int getSlotSize(ScreenHandlerType<?> screenHandlerType) {
        return slotSizeMap.getOrDefault(screenHandlerType, 0);
    }
}
