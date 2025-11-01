package sapv.terminalsolver.terminal.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.Slot;
import sapv.terminalsolver.mixin.HandledScreenAccessor;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Renderer;

public class NumbersRenderer implements Renderer {
    private static final int[] colors = new int[] { 0xFF0000FF, 0xFF4040FF, 0xFF8080FF };

    @Override
    public void render(Click[] solution, DrawContext context, Screen screen, float deltaTicks) {
        if (!(screen instanceof GenericContainerScreen container)) return;
        int min = Math.min(solution.length, colors.length);
        for (int i = 0; i < min; i++) {
            Click click = solution[i];
            Slot slot = container.getScreenHandler().getSlot(click.slot());
            HandledScreenAccessor accessor = (HandledScreenAccessor) screen;
            int x = accessor.getX() + slot.x;
            int y = accessor.getY() + slot.y;

            context.fill(x, y, x + 16, y + 16, colors[i]);
        }

    }
}
