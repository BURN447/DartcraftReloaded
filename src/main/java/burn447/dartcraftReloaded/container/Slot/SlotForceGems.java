package burn447.dartcraftReloaded.container.Slot;

import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by BURN447 on 6/29/2018.
 */
public class SlotForceGems extends SlotItemHandler {

    public SlotForceGems(IItemHandler handler, int index, int posX, int posY){
        super(handler, index, posX, posY);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ModItems.gemForceGem;
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return 64;
    }
}
