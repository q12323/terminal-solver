package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class BlueColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.BLUE_WOOL,
            Items.LAPIS_LAZULI,
            Items.BLUE_TERRACOTTA,
            Items.BLUE_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
