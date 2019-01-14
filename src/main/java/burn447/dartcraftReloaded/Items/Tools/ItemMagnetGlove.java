package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.Items.ItemBase;
import burn447.dartcraftReloaded.capablilities.Magnet.MagnetProvider;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_MAGNET;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

public class ItemMagnetGlove extends ItemBase {

    public ItemMagnetGlove(String name) {
        super(name);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_MAGNET, null))
            return new MagnetProvider(CAPABILITY_MAGNET, null);
        else
            return null;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(playerIn.isSneaking()) {
            if(playerIn.getHeldItem(handIn).hasCapability(CAPABILITY_MAGNET, null)) {
                if(playerIn.getHeldItem(handIn).getCapability(CAPABILITY_MAGNET, null).isActivated()){
                    playerIn.getHeldItem(handIn).getCapability(CAPABILITY_MAGNET, null).deactivate();
                }
                else
                    playerIn.getHeldItem(handIn).getCapability(CAPABILITY_MAGNET, null).activate();
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getCapability(CAPABILITY_MAGNET, null).isActivated()) {
            tooltip.add("Active");
        }
        else
            tooltip.add("Deactivated");
    }
}
