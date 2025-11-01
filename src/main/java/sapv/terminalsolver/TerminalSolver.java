package sapv.terminalsolver;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import sapv.terminalsolver.terminal.Terminal;

public class TerminalSolver {
    public static final TerminalSolver INSTANCE = new TerminalSolver();
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(INSTANCE::onStartTick);
    }
    private TerminalSolver() {}

    private boolean toggled = false;

    private Terminal currentTerminal;
    private ItemStack[] currentStacks = new ItemStack[0];
    private int currentSyncId = -1;

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public boolean isToggled() {
        return toggled;
    }

    private void onStartTick(MinecraftClient mc) {
//        currentTerminal = null;
//        if (!toggled) return;
//        if (!(mc.currentScreen instanceof GenericContainerScreen screen)) return;
//        Terminal terminal = Terminal.get(screen.getTitle().getString());
//        if (terminal == null) return;
//        currentTerminal = terminal;
//
//        int syncId = screen.getScreenHandler().syncId;
//        Inventory inventory = screen.getScreenHandler().getInventory();
//        if (currentSyncId != syncId) {
//            currentSyncId = syncId;
//            currentStacks = new ItemStack[inventory.size()];
//        }
//
//        for (int i = 0; i < currentStacks.length; i++) {
//            if (currentStacks[i] != null) continue;
//            currentStacks[i] = inventory.getStack(i);
//        }

    }

    public void onMainReceivePacket(Packet<?> packet) {
        switch (packet) {
            case OpenScreenS2CPacket p -> onOpenScreenPacket(p);
            default -> {}
        }

    }

    private void onOpenScreenPacket(OpenScreenS2CPacket packet) {
        currentTerminal = null;
        if (!toggled) return;
        switch (packet.getScreenHandlerType()) {
            case
        }
    }

    public void onPostScreenRender(DrawContext context, float deltaTicks) {
    }
}
