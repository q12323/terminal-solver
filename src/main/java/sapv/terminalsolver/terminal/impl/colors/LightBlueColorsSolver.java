package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class LightBlueColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.LIGHT_BLUE_WOOL,
            Items.LIGHT_BLUE_DYE,
            Items.LIGHT_BLUE_TERRACOTTA,
            Items.LIGHT_BLUE_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
