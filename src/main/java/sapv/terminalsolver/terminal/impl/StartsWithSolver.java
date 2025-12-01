package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.ItemStack;
import sapv.terminalsolver.TerminalSolverMod;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartsWithSolver implements Solver {
    public static final int SIZE = 45;
    private static final Set<Integer> allowedSlots = Set.of(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34);
    private static final Pattern pattern = Pattern.compile("^What starts with: '(\\w)'\\?$");
    public static String getStartsWithChar(String input) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) return null;
//        TerminalSolverMod.LOGGER.info("matched: {}", matcher.group(1).toLowerCase());
        return matcher.group(1).toLowerCase();
    }

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().size() != SIZE) return Click.EMPTY_SOLUTION;
        String c = getStartsWithChar(state.title().getString());
        assert c != null;
//        TerminalSolverMod.LOGGER.info("char {}", c);
        List<Click> solutions = new ArrayList<>();
        for (int slot : allowedSlots) {
            ItemStack stack = state.stacks().get(slot);
            if (stack.hasGlint()) continue;
            if (!stack.getName().getString().toLowerCase().startsWith(c)) continue;
            solutions.add(new Click(slot, 0, 1));
        }
        return solutions.toArray(Click.EMPTY_SOLUTION);
    }
}
