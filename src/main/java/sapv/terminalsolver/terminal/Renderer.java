package sapv.terminalsolver.terminal;

import net.minecraft.client.gui.DrawContext;

public interface Renderer {
    void render(Click[] solution, DrawContext context, float deltaTicks);
}
