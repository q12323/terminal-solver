package sapv.terminalsolver.terminal;

import sapv.terminalsolver.terminal.impl.ColorsRenderer;
import sapv.terminalsolver.terminal.impl.NumbersRenderer;
import sapv.terminalsolver.terminal.impl.NumbersSolver;
import sapv.terminalsolver.terminal.impl.colors.WhiteColorsSolver;

public enum TerminalType {
    NUMBERS(new NumbersRenderer(), new NumbersSolver(), (title, size) -> size == NumbersSolver.SIZE && "Click in order!".equals(title)),
//    WHITE_COLORS(ColorsRenderer.INSTANCE, new WhiteColorsSolver(), ((title, size) -> ))
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
    public interface TerminalValidator {
        boolean validate(String title, int size);
    }
}