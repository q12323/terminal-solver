package sapv.terminalsolver.terminal;

import net.minecraft.item.ItemStack;

public interface Solver {
    Click[] getSolution(ItemStack[] stacks);
}
