package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class GrayColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.GRAY_WOOL,
            Items.GRAY_DYE,
            Items.GRAY_TERRACOTTA,
            Items.GRAY_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
