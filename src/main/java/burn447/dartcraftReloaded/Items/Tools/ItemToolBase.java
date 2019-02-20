package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;


/**
 * Created by BURN447 on 3/26/2018.
 */
public class ItemToolBase extends Item {

    private String name;

    protected float efficiency;

    private final Set<Block> effectiveBlocks;

    public ItemToolBase(String name, Set<Block> effectiveBlocksIn) {
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
        this.setMaxStackSize(1);
        this.effectiveBlocks = effectiveBlocksIn;
        this.isDamageable();
        this.setMaxDamage(dartcraftReloaded.forceToolMaterial.getMaxUses());
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    protected void showDurabilityBar() {
        this.showDurabilityBar(this.getDefaultInstance());
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(2, attacker);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(1, entityLiving);
        }

        return true;
    }

    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return true;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List lores, ITooltipFlag flagIn) {
        ItemForceAxe.attatchInformation(stack, lores);
        super.addInformation(stack, worldIn, lores, flagIn);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

}