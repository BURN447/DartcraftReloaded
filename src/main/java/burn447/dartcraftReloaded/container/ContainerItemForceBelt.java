package burn447.dartcraftReloaded.container;

import burn447.dartcraftReloaded.Items.ItemForceBelt;
import burn447.dartcraftReloaded.Items.ItemForcePack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerItemForceBelt extends Container {

    private int numRows = 1;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    public ContainerItemForceBelt(IInventory playerInv, ItemStack fp) {

        int xPosC = 17;
        int yPosC = 19;
        //Maxes at 40
        int counter = 0;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 8; ++k)
            {
                this.addSlotToContainer(new SlotItemHandler(fp.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), counter, xPosC + k * 18, yPosC + j * 18) {
                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack) {
                        return !(stack.getItem() instanceof ItemForcePack || stack.getItem() instanceof ItemForceBelt);
                    }
                });
                counter++;
            }
        }

        //Player Inventory
        int xPos = 8;
        int yPos = 53;

        //Slots 9-99
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        //Slots 0-8
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }

    //Credit to Shadowfacts for this method
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(itemstack.getItem() instanceof ItemForceBelt)
                return ItemStack.EMPTY;

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

}
