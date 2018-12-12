package burn447.dartcraftReloaded.tileEntity;

import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityForceFurnace extends TileEntityFurnace {

    public static boolean isItemFuel(ItemStack stack) {
        return stack == ModItems.gemForceGem.getDefaultInstance();
    }

}
