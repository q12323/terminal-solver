package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class CyanColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.CYAN_WOOL,
            Items.CYAN_DYE,
            Items.CYAN_TERRACOTTA,
            Items.CYAN_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
