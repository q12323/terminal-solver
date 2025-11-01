package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class PurpleColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.PURPLE_WOOL,
            Items.PURPLE_DYE,
            Items.PURPLE_TERRACOTTA,
            Items.PURPLE_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
