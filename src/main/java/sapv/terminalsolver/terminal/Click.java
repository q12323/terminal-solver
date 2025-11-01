package sapv.terminalsolver.terminal;

public record Click(int slot, int button, int times) {
    public static final Click[] EMPTY_SOLUTION = new Click[0];
}
