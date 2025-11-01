package sapv.terminalsolver.mixin;

import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sapv.terminalsolver.TerminalSolver;
import sapv.terminalsolver.TerminalSolverMod;

@Mixin(ClientCommonNetworkHandler.class)
public class ClientCommonNetworkHandlerMixin {
    @Inject(method = "sendPacket", at = @At("RETURN"))
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {
        try {
            TerminalSolver.INSTANCE.onSendPacket(packet);
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Exception in sendPacket", e);
            throw new RuntimeException(e);
        }
    }
}
