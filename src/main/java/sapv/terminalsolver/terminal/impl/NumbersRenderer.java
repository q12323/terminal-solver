package sapv.terminalsolver.terminal.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.Slot;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Renderer;

public class NumbersRenderer implements Renderer {
    private static final int[] colors = new int[] { 0xFF0000FF, 0xFF5555FF, 0xFFAAAAFF };

    @Override
    public void render(Click[] solution, DrawContext context, Screen screen, float deltaTicks) {
        if (!(screen instanceof GenericContainerScreen container)) return;
        int current = 15 - solution.length;
        int min = Math.min(solution.length, colors.length);
        context.getMatrices().push();
        context.getMatrices().translate(0f, 0f, 251f);
        for (int i = 0; i < min; i++) {
            Click click = solution[i];
            Slot slot = container.getScreenHandler().getSlot(click.slot());
            drawSlotBox(context, screen, slot, colors[i]);
            drawSlotString(String.valueOf(current + i), context, screen, slot, 0xFFFFFFFF);
        }
        context.getMatrices().pop();

    }
}
