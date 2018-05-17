package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class DCRFuelHandler implements IFuelHandler {


    @Override
    public int getBurnTime(ItemStack fuel) {

        //100 per each item
        //Smelts 10 items
        if(fuel.getItem() == ModItems.goldenPowerSource){
            return 2000;
        }
        else
            return 0;
    }
}
