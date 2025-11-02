package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class ColorsSolver implements Solver {
    private static final Set<Integer> allowedSlots = Set.of(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43);
    public static final int SIZE = 54;

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().length != SIZE) return Click.EMPTY_SOLUTION;
        List<Click> solutions = new ArrayList<>();
        for (int slot : allowedSlots) {
            ItemStack stack = state.stacks()[slot];
            if (!isCorrectItem(stack.getItem())) continue;
            if (stack.hasGlint()) continue;
            solutions.add(new Click(slot, 0, 1));
        }
        return solutions.toArray(Click.EMPTY_SOLUTION);
    }

    protected abstract boolean isCorrectItem(Item item);
}
