package sapv.terminalsolver.terminal;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import sapv.terminalsolver.mixin.HandledScreenAccessor;

public interface Renderer {
    void render(Click[] solution, DrawContext context, Screen screen, float deltaTicks);

    default void drawSlotBox(DrawContext context, Screen screen, Slot slot, int argb) {
        HandledScreenAccessor accessor = (HandledScreenAccessor) screen;
        int x = accessor.getX() + slot.x;
        int y = accessor.getY() + slot.y;

        context.fill(x, y, x + 16, y + 16, argb);
    }

    default void drawSlotString(String string, DrawContext context, Screen screen, Slot slot, int rgb) {
        TextRenderer textRenderer = screen.getTextRenderer();
        HandledScreenAccessor accessor = (HandledScreenAccessor) screen;
        int x = accessor.getX() + slot.x + 8 - textRenderer.getWidth(string) / 2;
        int y = accessor.getY() + slot.y + 8 - textRenderer.fontHeight / 2;

        context.drawText(textRenderer, string, x, y, rgb, true);
    }
}
