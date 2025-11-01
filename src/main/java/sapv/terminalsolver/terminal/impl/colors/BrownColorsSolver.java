package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class BrownColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.BROWN_WOOL,
            Items.COCOA_BEANS,
            Items.BROWN_TERRACOTTA,
            Items.BROWN_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
