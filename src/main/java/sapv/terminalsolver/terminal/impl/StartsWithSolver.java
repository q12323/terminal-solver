package sapv.terminalsolver.terminal.impl;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import sapv.terminalsolver.terminal.Click;
import sapv.terminalsolver.terminal.ClickHistoryTerminalState;
import sapv.terminalsolver.terminal.Solver;
import sapv.terminalsolver.terminal.TerminalState;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartsWithSolver implements Solver {
    public static final int SIZE = 45;
    private static final Set<Integer> allowedSlots = Set.of(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34);
    private static final Pattern pattern = Pattern.compile("^What starts with: '(\\w)'\\?$");

    @Nullable
    public static String getStartsWithChar(String input) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) return null;
        return matcher.group(1).toLowerCase();
    }

    @Override
    public Click[] getSolutions(TerminalState state) {
        if (state.stacks().size() != SIZE) return Click.EMPTY_SOLUTION;
        String c = getStartsWithChar(state.title().getString());
        assert c != null;
        DefaultedList<ItemStack> stacks = state.stacks();
        if (state instanceof ClickHistoryTerminalState clickedSlotsState) {
            return allowedSlots.stream()
                    .filter(slot -> !clickedSlotsState.hasClick(slot))
                    .filter(slot -> stacks.get(slot).getName().getString().toLowerCase().startsWith(c))
                    .map(slot -> new Click(slot, 0, 1))
                    .toArray(Click[]::new);
        } else {
            return allowedSlots.stream()
                    .filter(slot -> {
                        ItemStack stack = stacks.get(slot);
                        return !stack.hasGlint() && stack.getName().getString().toLowerCase().startsWith(c);
                    })
                    .map(slot -> new Click(slot, 0, 1))
                    .toArray(Click[]::new);
        }
    }
}
