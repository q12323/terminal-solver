package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class YellowColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.YELLOW_WOOL,
            Items.YELLOW_DYE,
            Items.YELLOW_TERRACOTTA,
            Items.YELLOW_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
