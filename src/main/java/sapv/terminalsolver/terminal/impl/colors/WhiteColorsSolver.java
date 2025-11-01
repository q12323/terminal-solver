package sapv.terminalsolver.terminal.impl.colors;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import sapv.terminalsolver.terminal.impl.ColorsSolver;

import java.util.Set;

public class WhiteColorsSolver extends ColorsSolver {
    private static final Set<Item> items = Set.of(
            Items.WHITE_WOOL,
            Items.BONE_MEAL,
            Items.WHITE_TERRACOTTA,
            Items.WHITE_STAINED_GLASS
    );

    @Override
    protected boolean isCorrectItem(Item item) {
        return items.contains(item);
    }
}
