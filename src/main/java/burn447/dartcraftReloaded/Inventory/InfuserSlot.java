package burn447.dartcraftReloaded.Inventory;

import burn447.dartcraftReloaded.blocks.ModBlocks;
import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Created by BURN447 on 5/8/2018.
 */
public class InfuserSlot extends Slot {

    private NonNullList<Item> validInfuserModifiers = populateModifierList();

    public InfuserSlot(IInventory inventoryIn, int index, int xPosition, int yPosition){
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack item){

        for(Item stack : validInfuserModifiers) {
            if (item.getItem() == stack) {
                return true;
            }
        }
        return false;
    }

    private NonNullList<Item> populateModifierList(){
        NonNullList<Item> list = NonNullList.<Item>withSize(25, null);

        list.add(ModItems.nuggetForce);
        list.add(Items.SUGAR);
        list.add(ModBlocks.forceLog.createItemBlock());
        list.add(Items.FLINT);
        list.add(Items.EXPERIENCE_BOTTLE);

        return list;
    }
}
