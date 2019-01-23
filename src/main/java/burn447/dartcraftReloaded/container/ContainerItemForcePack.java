package burn447.dartcraftReloaded.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerItemForcePack extends Container {

    int numRows = 5;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    public ContainerItemForcePack(IInventory playerInv, ItemStack fp) {

        int xPosC = 17;
        int yPosC = 20;
        int counter = 0;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 8; ++k)
            {
                this.addSlotToContainer(new SlotItemHandler(fp.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), counter, 8 + k * 18, 18 + j * 18));
                counter++;
            }
        }

        //Player Inventory
        int xPos = 8;
        int yPos = 127;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }


}
