package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedGreenSolver implements Solver {
    private static final Set<Integer> allowedSlots = Set.of(11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33);
    public static final int SIZE = 45;

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().size() != SIZE) return Click.EMPTY_SOLUTION;
        List<Click> solutions = new ArrayList<>();
        allowedSlots.forEach(slot -> {
            ItemStack stack = state.stacks().get(slot);
            if (stack.getItem() == Items.RED_STAINED_GLASS_PANE) {
                solutions.add(new Click(slot, 0, 1));
            }
        });
        return solutions.toArray(Click.EMPTY_SOLUTION);
    }
}
