package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.StringHelper;
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
        this.maxStackSize = 1;
        this.effectiveBlocks = effectiveBlocksIn;
        this.showDurabilityBar(this.getDefaultInstance());
        this.isDamageable();
        this.setMaxDamage(dartcraftReloaded.forceToolMaterial.getMaxUses());
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return efficiency;
        }
        return this.effectiveBlocks.contains(state.getBlock()) ? stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() : 1.0F;
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
        if(CAPABILITY_TOOLMOD != null)
            return stack.getCapability(CAPABILITY_TOOLMOD, null).getItemEnchantibility();
        else
            return 0;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
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
        if (stack.getCapability(CAPABILITY_TOOLMOD, null) != null) {
            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 12.0F) {
                lores.add("Speed I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 16.0F) {
                lores.add("Speed II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasHeat()) {
                lores.add("Heat");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 1.5F) {
                lores.add("Force I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 2.0F) {
                lores.add("Force II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasGrinding()) {
                lores.add("Grinding");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasTouch()) {
                lores.add("Silk Touch");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 12.0F) {
                lores.add("Damage I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 16.0F) {
                lores.add("Damage II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasLumberJack()) {
                lores.add("LumberJack");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasEnder()) {
                lores.add("Ender");
            }

            if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(4))
                lores.add("Bleeding IV");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(3))
                lores.add("Bleeding III");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2))
                lores.add("Bleeding II");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1))
                lores.add("Bleeding I");

            if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBane())
                lores.add("Bane - Does not prevent teleportaion in rain currently - Bug? Not sure why");
            }
            super.addInformation(stack, worldIn, lores, flagIn);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

}