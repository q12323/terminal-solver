package sapv.terminalsolver.mixin;

import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sapv.terminalsolver.Dispatcher;

@Mixin(NetworkThreadUtils.class)
public class NetworkThreadUtilsMixin {
    @Inject(
            method = "method_11072(Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/network/packet/Packet;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/Packet;apply(Lnet/minecraft/network/listener/PacketListener;)V")
    )
    private static <T extends PacketListener> void method_11072(T packetListener, Packet<T> packet, CallbackInfo ci) {
        Dispatcher.onMainReceivePacket(packet);
    }
}