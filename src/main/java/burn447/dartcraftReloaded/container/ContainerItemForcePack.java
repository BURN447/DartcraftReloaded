package burn447.dartcraftReloaded.container;

import burn447.dartcraftReloaded.Items.ItemForcePack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerItemForcePack extends Container {

    private int numRows = 5;

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
                this.addSlotToContainer(new SlotItemHandler(fp.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), counter, xPosC + k * 18, yPosC + j * 18));
                counter++;
            }
        }

        //Player Inventory
        int xPos = 8;
        int yPos = 126;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(itemstack1.getItem() instanceof ItemForcePack) {
                return ItemStack.EMPTY;
            }

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

}
