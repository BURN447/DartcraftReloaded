package burn447.dartcraftReloaded.container.Slot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by BURN447 on 6/29/2018.
 */
public class SlotForceBook extends SlotItemHandler {

    public SlotForceBook(IItemHandler handler, int index, int posX, int posY){
        super(handler, index, posX, posY);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == Items.BOOK;
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return 1;
    }
}
