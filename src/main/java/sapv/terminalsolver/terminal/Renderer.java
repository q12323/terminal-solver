package sapv.terminalsolver.terminal;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public interface Renderer {
    void render(Click[] solution, DrawContext context, Screen screen, float deltaTicks);
}
