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

        this.addSlotToContainer(new SlotItemHandler(te.handler, 2, 80, 20));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 4, 104, 32));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 3, 116, 57));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 5, 104, 81));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 6, 80, 93));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 7, 56, 81));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 8, 44, 57));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 9, 56, 32));
        this.addSlotToContainer(new SlotItemHandler(te.handler, 10, 80, 57));

        //Force Book Slot
        this.addSlotToContainer(new SlotForceBook(te.handler, 1, 10, 10));

        //Force Gem Slot
        this.addSlotToContainer(new SlotForceGems(te.handler, 0, 10, 35));

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

    //Credits to Shadowfactsdev https://github.com/shadowfacts/ShadowMC/blob/1.11/src/main/java/net/shadowfacts/shadowmc/inventory/ContainerBase.java
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        //empty ItemStack
        ItemStack itemstack = ItemStack.EMPTY;
        //Slot we're trying to insert into
        Slot slot = inventorySlots.get(index);

        //If slot is not null and already has a stack
        if (slot != null && slot.getHasStack()) {
            //get stack already in the slot
            ItemStack itemstack1 = slot.getStack();
            //makes a copy of stack in slot of index
            itemstack = itemstack1.copy();

            //Sixe of container slots
            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            //index is in player inventory
            if (index < containerSlots) {
                //true if can merge together
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                //Merges into container inventory
            } else {
                if(index == 10) {
                    if (!this.mergeItemStack(itemstack1, 3, 39, true))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            //If count == 0, empty stack
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                //Marks Dirty
                slot.onSlotChanged();
            }


            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            //Marks Dirty
            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection) {
            i = endIndex - 1;
        }

        if (stack.isStackable()) {
            while (stack.getCount() > 0 && (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex)) {
                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (slot.isItemValid(stack)) {
                    if (!itemstack.isEmpty() && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                        int j = itemstack.getCount() + stack.getCount();

                        if (j <= stack.getMaxStackSize()) {
                            stack.setCount(0);
                            itemstack.setCount(j);
                            slot.onSlotChanged();
                            flag = true;
                        } else if (itemstack.getCount() < stack.getMaxStackSize()) {
                            stack.shrink(stack.getMaxStackSize() - itemstack.getCount());
                            itemstack.setCount(stack.getMaxStackSize());
                            slot.onSlotChanged();
                            flag = true;
                        }
                    }
                }

                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        if (stack.getCount() > 0) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }

            while (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex) {
                Slot slot1 = (Slot)this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();

                // Forge: Make sure to respect isItemValid in the slot.
                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    slot1.putStack(stack.copy());
                    slot1.onSlotChanged();
                    stack.setCount(0);
                    flag = true;
                    break;
                }

                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }

        return flag;
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