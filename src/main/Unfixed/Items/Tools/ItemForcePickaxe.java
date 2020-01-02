package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForcePickaxe extends ItemPickaxe {

    private static String name;

    public ItemForcePickaxe(String name) {
        super(dartcraftReloaded.forceToolMaterial);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_TOOLMOD, null))
            return new ToolModProvider(CAPABILITY_TOOLMOD, null);
        else
            return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List toolTip, ITooltipFlag flagIn) {
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasSpeed())
            toolTip.add("Speed " + stack.getCapability(CAPABILITY_TOOLMOD, null).getSpeedLevel());
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasHeat())
            toolTip.add("Heat");
        super.addInformation(stack, worldIn, toolTip, flagIn);
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}