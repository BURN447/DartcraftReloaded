package burn447.dartcraftReloaded.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/11/2018.
 */
public class ItemForcePack extends ItemBase {

    public ItemForcePack(String name){
        super(name);
        inventory = new ItemStackHandler(40);
    }

    IItemHandler inventory;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
            return new ICapabilityProvider() {
                @Override
                public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                        return true;
                    return false;
                }

                @Nullable
                @Override
                public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                        return (T) inventory;
                    return null;
                }
            };
        else
            return null;
    }
}
