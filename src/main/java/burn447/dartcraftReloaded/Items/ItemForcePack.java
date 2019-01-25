package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.Handlers.DCRGUIHandler;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/11/2018.
 */
public class ItemForcePack extends ItemBase {

    public final ItemStackHandler handler;

    public ItemForcePack(String name){
        super(name);
        handler = new ItemStackHandler(40);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(playerIn.getHeldItem(handIn).hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)){
            //x,y,z coordinates are not important afaik
            playerIn.openGui(dartcraftReloaded.instance, DCRGUIHandler.PACK, worldIn, 0, 0, 0);
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }
        //If it doesn't nothing bad happens
        //return super.onItemRightClick(worldIn, playerIn, handIn);
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }


    //Inspired by Botaina's Code

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new InventoryProvider();
    }

    private static class InventoryProvider implements ICapabilitySerializable<NBTBase> {

        private final IItemHandler inventory = new ItemStackHandler(40) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
            else return null;
        }

        @Override
        public NBTBase serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inventory, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inventory, null, nbt);
        }

        public IItemHandler getInventory() {
            return inventory;
        }
    }
}
