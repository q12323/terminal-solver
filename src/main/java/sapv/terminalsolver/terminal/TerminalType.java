package sapv.terminalsolver.terminal;

import sapv.terminalsolver.terminal.impl.*;
import sapv.terminalsolver.terminal.impl.colors.*;

public enum TerminalType {
    NUMBERS(new NumbersRenderer(), new NumbersSolver(), (title, size) -> size == NumbersSolver.SIZE && "Click in order!".equals(title)),
    WHITE_COLORS(SimpleRenderer.INSTANCE, new WhiteColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the WHITE items!".equals(title)),
    ORANGE_COLORS(SimpleRenderer.INSTANCE, new OrangeColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the ORANGE items!".equals(title)),
    MAGENTA_COLORS(SimpleRenderer.INSTANCE, new MagentaColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the MAGENTA items!".equals(title)),
    LIGHT_BLUE_COLORS(SimpleRenderer.INSTANCE, new LightBlueColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the LIGHT BLUE items!".equals(title)),
    YELLOW_COLORS(SimpleRenderer.INSTANCE, new YellowColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the YELLOW items!".equals(title)),
    LIME_COLORS(SimpleRenderer.INSTANCE, new LimeColorsSolver(), ((title, size) -> size == ColorsSolver.SIZE && "Select all the LIME items!".equals(title))),
    PINK_COLORS(SimpleRenderer.INSTANCE, new PinkColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the PINK items!".equals(title)),
    GRAY_COLORS(SimpleRenderer.INSTANCE, new GrayColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the GRAY items!".equals(title)),
    SILVER_COLORS(SimpleRenderer.INSTANCE, new SilverColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the SILVER items!".equals(title)),
    CYAN_COLORS(SimpleRenderer.INSTANCE, new CyanColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the CYAN items!".equals(title)),
    PURPLE_COLORS(SimpleRenderer.INSTANCE, new PurpleColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the PURPLE items!".equals(title)),
    BLUE_COLORS(SimpleRenderer.INSTANCE, new BlueColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the BLUE items!".equals(title)),
    BROWN_COLORS(SimpleRenderer.INSTANCE, new BrownColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the BROWN items!".equals(title)),
    GREEN_COLORS(SimpleRenderer.INSTANCE, new GreenColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the GREEN items!".equals(title)),
    RED_COLORS(SimpleRenderer.INSTANCE, new RedColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the RED items!".equals(title)),
    BLACK_COLORS(SimpleRenderer.INSTANCE, new BlackColorsSolver(), (title, size) -> size == ColorsSolver.SIZE && "Select all the BLACK items!".equals(title)),
    RUBIX(new RubixRenderer(), new RubixSolver(), (title, size) -> size == RubixSolver.SIZE && "Change all to same color!".equals(title)),
    STARTS_WITH(SimpleRenderer.INSTANCE, new StartsWithSolver(), (title, size) -> size == StartsWithSolver.SIZE && StartsWithSolver.getStartsWithChar(title) != null),
    RED_GREEN(new RedGreenRenderer(), new RedGreenSolver(), (title, size) -> size == RedGreenSolver.SIZE && "Correct all the panes!".equals(title)),
    MELODY(SimpleRenderer.INSTANCE, state -> Click.EMPTY_SOLUTION, (title, size) -> size == 54 && "Click the button on time!".equals(title)),
    ;

    public final Renderer renderer;
    public final Solver solver;
    private final TerminalValidator validator;

    TerminalType(Renderer renderer, Solver solver, TerminalValidator validator) {
        this.renderer = renderer;
        this.solver = solver;
        this.validator = validator;
    }

    public static TerminalType get(String title, int size) {
        for (TerminalType o : values()) {
            if (o.validator.validate(title, size)) {
                return o;
            }
        }
        return null;
    }

    @FunctionalInterface
    private interface TerminalValidator {
        boolean validate(String title, int size);
    }
}