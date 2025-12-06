package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.Comparator;
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
        List<ItemStack> stacks = state.stacks();
        if (stacks.size() != SIZE) return Click.EMPTY_SOLUTION;
        return orderMap.values().stream().mapToInt(Integer::intValue)
                .mapToObj(to -> allowedSlots.stream().mapToInt(Integer::intValue)
                        .mapToObj(slot -> {
                            int from = orderMap.getOrDefault(stacks.get(slot).getItem(), to);
                            int offset = getClickOffset(from, to);
                            if (offset > 0) {
                                return new Click(slot, 0, offset);
                            } else {
                                return new Click(slot, 1, -offset);
                            }
                        })
                        .filter(click -> click.times() != 0)
                        .toList())
                .min(Comparator.comparingInt(clicks -> clicks.stream()
                        .mapToInt(Click::times)
                        .sum()))
                .map(solutions -> solutions.toArray(Click.EMPTY_SOLUTION))
                .orElse(Click.EMPTY_SOLUTION);
    }

    private static int getClickOffset(int from, int to) {
        int offset = (to - from + 5) % 5;
        if (offset > 2) {
            offset = offset - 5;
        }
        return offset;
    }
}
