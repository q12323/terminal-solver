package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class RedColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.RED_WOOL,
            Items.RED_DYE,
            Items.RED_TERRACOTTA,
            Items.RED_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
