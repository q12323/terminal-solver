package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class OrangeColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.ORANGE_WOOL,
            Items.ORANGE_DYE,
            Items.ORANGE_TERRACOTTA,
            Items.ORANGE_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
