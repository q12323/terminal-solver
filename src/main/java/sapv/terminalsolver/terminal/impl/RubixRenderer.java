package sapv.terminalsolver.terminal.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.Slot;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Renderer;

public class RubixRenderer implements Renderer {
    @Override
    public void render(Click[] solution, DrawContext context, Screen screen, float deltaTicks) {
        if (!(screen instanceof GenericContainerScreen container)) return;
        context.getMatrices().push();
        context.getMatrices().translate(0f, 0f, 1000f);
        for (Click click : solution) {
            Slot slot = container.getScreenHandler().getSlot(click.slot());
//            drawSlotBox(context, screen, slot, 0xFF0000FF);
            drawSlotString(String.valueOf(click.button() == 0 ? click.times() : -click.times()), context, screen, slot, 0xFFFFFF);
        }
        context.getMatrices().pop();
    }
}
