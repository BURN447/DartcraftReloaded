package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.Tools.ToolModified;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import com.sun.webkit.dom.DOMImplementationImpl;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import static burn447.dartcraftReloaded.proxy.CommonProxy.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 3/26/2018.
 */
public class ItemToolBase extends Item implements Capability.IStorage<IToolModifier> {

    private final Set<Block> effectiveBlocks;
    public float efficiency;
    public float attackDamage;
    public float attackSpeed;
    protected Item.ToolMaterial toolMaterial;
    private String name;

    public ItemToolBase(String name, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn){
        //super();

        this.toolMaterial = materialIn;
        this.effectiveBlocks = effectiveBlocksIn;
        this.maxStackSize = 1;
        this.efficiency = IToolModifier.efficiency;
        this.setMaxDamage(materialIn.getMaxUses());
        this.attackDamage = attackDamageIn + materialIn.getAttackDamage();
        this.attackSpeed = attackSpeedIn;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List lores, ITooltipFlag flagIn)
    {
            lores.add("No Modifers" + efficiency);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return efficiency;
        }
        return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiency : 1.0F;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(2, attacker);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }

        return true;
    }

    public boolean isFull3D()
    {
        return true;
    }

    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    public static void init(){

    }

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbt) {

    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ICapabilityProvider() {
            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                if(capability == CAPABILITY_TOOLMOD)
                    return true;
                else
                    return false;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                return null;
            }
        };
    }
}


