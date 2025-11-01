package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class GreenColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.GREEN_WOOL,
            Items.GREEN_DYE,
            Items.GREEN_TERRACOTTA,
            Items.GREEN_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
