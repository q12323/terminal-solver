package sapv.terminalsolver.terminal;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

import java.util.regex.Pattern;

public enum Terminal {
    A(null, null, "^[\\d]*$");

    public final Renderer renderer;
    public final Solver solver;
    private final Pattern pattern;

    Terminal(Renderer renderer, Solver solver, String titleRegex) {
        this.renderer = renderer;
        this.solver = solver;
        this.pattern = Pattern.compile(titleRegex);
    }

    public void render(ItemStack[] stacks, DrawContext context, float deltaTicks) {
        renderer.render(solver.getSolution(stacks), context, deltaTicks);
    }

    private boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    public static Terminal get(String title) {
        for (Terminal o : values()) {
            if (o.matches(title)) {
                return o;
            }
        }
        return null;
    }

}