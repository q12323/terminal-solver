package sapv.terminalsolver.terminal;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;

public record TerminalState(@NotNull TerminalType type, Text title, int syncId, @NotNull DefaultedList<ItemStack> stacks) {
    public Click[] getSolutions() {
        return type.solver.getSolutions(this);
    }
}
