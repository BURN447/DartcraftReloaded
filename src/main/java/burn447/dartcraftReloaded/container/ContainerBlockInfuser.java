package burn447.dartcraftReloaded.container;

import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

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
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        boolean hasReturned = false;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 11)
            {
                if (!this.mergeItemStack(itemstack1, 11, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else {
                hasReturned = false;
                for (int i = 0; i < 11; i++) {
                    if (hasReturned == false) {
                        if (((Slot) this.inventorySlots.get(i)).getHasStack() || !((Slot) this.inventorySlots.get(i)).isItemValid(itemstack1)) {
                            //hasReturned = true;
                            return ItemStack.EMPTY;
                        }
                    }
                    if (hasReturned == false) {
                        if (itemstack1.hasTagCompound() && itemstack1.getCount() == 1) {
                            ((Slot) this.inventorySlots.get(i)).putStack(itemstack1.copy());
                            itemstack1.setCount(0);
                            hasReturned = true;
                        } else if (!itemstack1.isEmpty()) {
                            ((Slot) this.inventorySlots.get(i)).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getMetadata()));
                            itemstack1.shrink(1);
                            hasReturned = true;
                        }
                    }
                }
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
