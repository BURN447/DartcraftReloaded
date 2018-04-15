package burn447.dartcraftReloaded.container;

import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class ContainerBlockInfuser extends Container {

    IItemHandler handler;
    private TileEntityInfuser te;


    public ContainerBlockInfuser(IInventory playerInv, TileEntityInfuser te) {

        this.te = te;

        handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 10, 10));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 10, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 80, 20));
        this.addSlotToContainer(new SlotItemHandler(handler, 3, 104, 32));
        this.addSlotToContainer(new SlotItemHandler(handler, 4, 116, 57));
        this.addSlotToContainer(new SlotItemHandler(handler, 5, 104, 81));
        this.addSlotToContainer(new SlotItemHandler(handler, 6, 80, 93));
        this.addSlotToContainer(new SlotItemHandler(handler, 7, 56, 81));
        this.addSlotToContainer(new SlotItemHandler(handler, 8, 44, 57));
        this.addSlotToContainer(new SlotItemHandler(handler, 9, 56, 32));
        this.addSlotToContainer(new SlotItemHandler(handler, 10, 80, 57));

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

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < this.handler.getSlots()) {
                if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
                    return ItemStack.EMPTY;
            } else {
                //if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
                //    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }


}
