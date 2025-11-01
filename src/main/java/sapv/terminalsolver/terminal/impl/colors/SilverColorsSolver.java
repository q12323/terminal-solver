package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class SilverColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.LIGHT_GRAY_WOOL,
            Items.LIGHT_GRAY_DYE,
            Items.LIGHT_GRAY_TERRACOTTA,
            Items.LIGHT_GRAY_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
