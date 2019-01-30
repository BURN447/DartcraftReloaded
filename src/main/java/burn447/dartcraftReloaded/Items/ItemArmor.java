package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_PLAYERMOD;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 3/4/2018.
 */
public class ItemArmor extends net.minecraft.item.ItemArmor {

    private String name;

    public ItemArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, 0, slot);
        setRegistryName(name);
        setTranslationKey(name);
        this.name = name;
        setCreativeTab(dartcraftReloaded.creativeTab);
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBane())
            tooltip.add("Bane");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasCamo())
            tooltip.add("Camo");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasHeat())
            tooltip.add("Heat");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasSpeed())
            tooltip.add("Speed");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasDamage())
            tooltip.add("Damage");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasWing())
            tooltip.add("Wing");
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
