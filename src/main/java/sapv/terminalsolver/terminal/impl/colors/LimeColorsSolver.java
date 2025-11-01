package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class LimeColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.LIME_WOOL,
            Items.LIME_DYE,
            Items.LIME_TERRACOTTA,
            Items.LIME_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
