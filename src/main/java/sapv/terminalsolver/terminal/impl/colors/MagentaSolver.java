package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class MagentaSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.MAGENTA_WOOL,
            Items.MAGENTA_DYE,
            Items.MAGENTA_TERRACOTTA,
            Items.MAGENTA_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
