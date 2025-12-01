package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.*;

public class NumbersSolver implements Solver {
    private static final Set<Integer> allowedSlots = Set.of(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25);
    public static final int SIZE = 36;

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().size() != SIZE) throw new IllegalArgumentException();
        List<Click> solution = new ArrayList<>();
        for (int slot : allowedSlots) {
            ItemStack stack = state.stacks().get(slot);
            if (!isRedGlassPane(stack)) continue;
            solution.add(new Click(slot, 0, 1));
        }
        solution.sort(Comparator.comparingInt(click -> state.stacks().get(click.slot()).getCount()));
        return solution.toArray(Click.EMPTY_SOLUTION);
    }

    private static boolean isRedGlassPane(ItemStack stack) {
        return stack.getItem() == Items.RED_STAINED_GLASS_PANE;
    }
}
