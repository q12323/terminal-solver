package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RubixSolver implements Solver {
    public static final int SIZE = 45;
    private static final Set<Integer> allowedSlots = Set.of(12, 13, 14, 21, 22, 23, 30, 31, 32);

    private static final Map<Item, Integer> orderMap = Map.of(
            Items.RED_STAINED_GLASS_PANE, 0,
            Items.ORANGE_STAINED_GLASS_PANE, 1,
            Items.YELLOW_STAINED_GLASS_PANE, 2,
            Items.GREEN_STAINED_GLASS_PANE, 3,
            Items.BLUE_STAINED_GLASS_PANE, 4
    );

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().length != SIZE) return Click.EMPTY_SOLUTION;
        List<Click[]> solutionsList = new ArrayList<>(orderMap.size());

        orderMap.keySet().forEach(to -> {
            List<Click> solutions = new ArrayList<>();
            for (int slot : allowedSlots) {
                Item item = state.stacks()[slot].getItem();
                if (!orderMap.containsKey(item)) return;
                int offset = getClickOffset(item, to);
                if (offset == 0) continue;
                if (offset > 0) {
                    solutions.add(new Click(slot, 0, offset));
                } else {
                    solutions.add(new Click(slot, 1, -offset));
                }
            }
            solutionsList.add(solutions.toArray(Click.EMPTY_SOLUTION));
        });

        Click[] solutions = solutionsList.stream().reduce((min, cur) -> {
            if (cur.length < min.length)  return cur;
            return min;
        }).orElse(Click.EMPTY_SOLUTION);

        return solutions;
    }

    private static int getClickOffset(Item from, Item to) {
        int fromIndex = orderMap.get(from);
        int toIndex = orderMap.get(to);
        return toIndex - fromIndex;
    }
}
