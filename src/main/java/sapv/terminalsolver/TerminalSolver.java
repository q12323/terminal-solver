package sapv.terminalsolver;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.TerminalType;
import sapv.terminalsolver.terminal.TerminalState;

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
    }

    void onPostScreenRender(DrawContext context, Screen screen, float deltaTicks) {
        if (!toggled) return;
        if (currentTerminalState == null) return;
        currentTerminalState.type().renderer.render(cachedSolution, context, screen, deltaTicks);
    }

    void onSendPacket(Packet<?> packet) {
        if (packet instanceof CloseHandledScreenC2SPacket) {
            currentTerminalState = null;
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

    private void onOpenScreenPacket(OpenScreenS2CPacket packet) {
        currentTerminalState = null;
        if (!toggled) return;
        int size = getSlotSize(packet.getScreenHandlerType());
        if (size == 0) return;
        TerminalType type = TerminalType.get(packet.getName().getString(), size);
        if (type == null) return;
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(size, ItemStack.EMPTY);
        currentTerminalState = new TerminalState(type, packet.getName(), packet.getSyncId(), stacks);
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
