package sapv.terminalsolver.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sapv.terminalsolver.Dispatcher;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;runTasks()V", shift = At.Shift.AFTER)
    )
    public void runTasks(boolean tick, CallbackInfo ci) {
        Dispatcher.onPostRunTasks();
    }
}
