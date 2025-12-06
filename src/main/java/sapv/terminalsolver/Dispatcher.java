package sapv.terminalsolver;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.packet.Packet;

public class Dispatcher {
    private static final TerminalSolver termSolver = TerminalSolver.INSTANCE;

    public static void onSendPacket(Packet<?> packet) {
        try {
            termSolver.onSendPacket(packet);
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Exception in sendPacket", e);
            throw new RuntimeException(e);
        }
    }

    public static void onMainReceivePacket(Packet<?> packet) {
        try {
            termSolver.onMainReceivePacket(packet);
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Exception in mainReceivePacket", e);
            throw new RuntimeException(e);
        }
    }

    public static void onPostScreenRender(DrawContext context, Screen screen, float deltaTicks) {
        try {
            termSolver.onPostScreenRender(context, screen, deltaTicks);
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Exception in postScreenRender", e);
            throw new RuntimeException(e);
        }
    }

    public static void onPostRunTasks() {
        try {
            termSolver.onPostRunTasks();
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Exception in postRunTasks", e);
            throw new RuntimeException(e);
        }
    }
}
