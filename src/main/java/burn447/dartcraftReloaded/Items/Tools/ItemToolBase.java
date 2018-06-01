package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.capablilities.ToolModProvider;
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

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;


/**
 * Created by BURN447 on 3/26/2018.
 */
public class ItemToolBase extends Item {

    private String name;

    protected float efficiency;

    public ItemToolBase(String name){
        //super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
        this.maxStackSize = 1;
        this.showDurabilityBar(this.getDefaultInstance());
        this.isDamageable();
        this.setMaxDamage(dartcraftReloaded.forceToolMaterial.getMaxUses());
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        efficiency = stack.getCapability(CAPABILITY_TOOLMOD, null).getDestroySpeed(stack, state);

        return efficiency;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(2, attacker);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            //System.out.println("Damaging Tool");
            System.out.println(this.getDurabilityForDisplay(stack));
            stack.damageItem(1, entityLiving);
        }

        return true;
    }

    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack)
    {
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
            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 10.0F) {
                lores.add("Speed One");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 14.0F) {
                lores.add("Speed Two");
            }
        }
    }
}


