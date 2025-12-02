package sapv.terminalsolver.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sapv.terminalsolver.TerminalSolver;
import sapv.terminalsolver.TerminalSolverMod;

@Mixin(Screen.class)
abstract class ScreenMixin {
    @SuppressWarnings("ConstantConditions")
    @Inject(method = "renderWithTooltip", at = @At("RETURN"))
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        try {
            TerminalSolver.INSTANCE.onPostScreenRender(context, (Screen) (Object) this, deltaTicks);
        } catch (Exception e) {
            TerminalSolverMod.LOGGER.error("Error while rendering screen", e);
            throw new RuntimeException(e);
        }
    }
}
