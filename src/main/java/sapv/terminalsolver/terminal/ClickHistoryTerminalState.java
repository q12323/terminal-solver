package sapv.terminalsolver.terminal;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClickHistoryTerminalState extends TerminalState implements Iterable<Integer> {
    private final Set<Integer> clickedSlots = new HashSet<>();

    public ClickHistoryTerminalState(@NotNull TerminalType type, Text title, int syncId, @NotNull DefaultedList<ItemStack> stacks) {
        super(type, title, syncId, stacks);
    }

    public void addClick(int slot) {
        clickedSlots.add(slot);
    }

    public boolean hasClick(int slot) {
        return clickedSlots.contains(slot);
    }

    @Override
    public @NotNull Iterator<Integer> iterator() {
        return clickedSlots.iterator();
    }
}
