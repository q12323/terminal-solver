package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class PinkColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.PINK_WOOL,
            Items.PINK_DYE,
            Items.PINK_TERRACOTTA,
            Items.PINK_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
