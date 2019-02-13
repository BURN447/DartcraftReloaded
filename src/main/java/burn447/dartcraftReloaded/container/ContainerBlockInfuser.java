package burn447.dartcraftReloaded.container;

import burn447.dartcraftReloaded.container.Slot.SlotForceBook;
import burn447.dartcraftReloaded.container.Slot.SlotForceGems;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class ContainerBlockInfuser extends Container {

    TileEntityInfuser te;

    public ContainerBlockInfuser(IInventory playerInv, TileEntityInfuser te) {

        //Modifier Slots
        this.addSlotToContainer(new SlotItemHandler(te.handler, 0, 80, 20));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 1, 104, 32));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 2, 116, 57));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 3, 104, 81));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 4, 80, 93));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 5, 56, 81));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 6, 44, 57));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 7, 56, 32));

        //Tools Slot
        this.addSlotToContainer(new SlotItemHandler(te.handler, 8, 80, 57));

        //Force Gem Slot
        this.addSlotToContainer(new SlotForceGems(te.handler, 9, 9, 11));

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

        this.te = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        boolean hasReturned = false;

        if (slot != null && slot.getHasStack()) {
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
                for (int i = 0; i < 11; i++) {
                    if (!hasReturned) {
                        if (((Slot) this.inventorySlots.get(i)).getHasStack() || !((Slot) this.inventorySlots.get(i)).isItemValid(itemstack1)) {
                            hasReturned = true;
                            return ItemStack.EMPTY;
                        }
                    }
                    if (!hasReturned) {
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

    public void setButtonPressed(boolean buttonPressed){
        te.canWork = buttonPressed;
    }

    public void setFluidAmount(int amount){
        te.fluidContained = amount;
    }

    public int getFluidAmount(){
        return te.fluidContained;
    }

    public TileEntity getTE(){
        return te;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }
}