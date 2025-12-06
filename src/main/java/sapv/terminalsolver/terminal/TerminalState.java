package sapv.terminalsolver.terminal;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;

public class TerminalState {
    @NotNull
    private final TerminalType type;
    private final Text title;
    private final int syncId;
    @NotNull
    private final DefaultedList<ItemStack> stacks;

    public TerminalState(@NotNull TerminalType type, Text title, int syncId, @NotNull DefaultedList<ItemStack> stacks) {
        this.type = type;
        this.title = title;
        this.syncId = syncId;
        this.stacks = stacks;
    }

    public Click[] getSolutions() {
        return type.solver.getSolutions(this);
    }

    public @NotNull TerminalType type() {
        return type;
    }

    public Text title() {
        return title;
    }

    public int syncId() {
        return syncId;
    }

    public @NotNull DefaultedList<ItemStack> stacks() {
        return stacks;
    }
}
