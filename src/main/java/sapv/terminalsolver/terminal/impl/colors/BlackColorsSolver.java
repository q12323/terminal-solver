package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class BlackColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.BLACK_WOOL,
            Items.INK_SAC,
            Items.BLACK_TERRACOTTA,
            Items.BLACK_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
