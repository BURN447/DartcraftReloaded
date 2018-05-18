package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.Tools.ToolModified;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
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
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by BURN447 on 3/26/2018.
 */
public class ItemToolBase extends Item {

    private final Set<Block> effectiveBlocks;
    public float efficiency;
    public float attackDamage;
    public float attackSpeed;
    protected Item.ToolMaterial toolMaterial;
    private String name;
    public boolean speedMod1 = false, speedMod2 = false;

    public ItemToolBase(String name, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn){
        //super();

        this.toolMaterial = materialIn;
        this.effectiveBlocks = effectiveBlocksIn;
        this.maxStackSize = 1;
        this.efficiency = materialIn.getEfficiency();
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

    public ItemStack addSpeedMod(ItemStack stack){
        NBTTagCompound nbt;
        if(stack.hasTagCompound()){
            nbt = stack.getTagCompound();
        }
        else
            nbt = new NBTTagCompound();

        if(!speedMod1){
            nbt.setFloat("speedMod", 10.0F);
            this.efficiency = 10.0F;
            speedMod1 = true;
        }
        else if(!speedMod2){
            nbt.setFloat("speedMod", 15.0F);
            this.efficiency = 15.0F;
            speedMod2 = true;
        }
        else
            nbt.setFloat("speedMod", 4.0F);

        stack.setTagCompound(nbt);

        return stack;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List lores, ITooltipFlag flagIn)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("speedMod") && stack.hasTagCompound())
        {
            lores.add("Speed 1\n" + Float.toString(stack.getTagCompound().getFloat("speedMod")));
        }
        else
            lores.add("No Modifers");
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
        CapabilityManager.INSTANCE.register(IToolModifier.class, new Capability.IStorage<IToolModifier>() {
            @Override
            public NBTTagCompound writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side){
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbt){
                if(nbt instanceof NBTTagCompound){
                    instance.deserializeNBT(((NBTTagCompound) nbt));
                }
            }
        }, IToolModifier.class);
        System.out.println("REGISTERED CAPABILITY");
    }

    @SubscribeEvent
    public void onAddCapabilitiesItemStack(AttachCapabilitiesEvent<Item> e){
        //e.addCapability(IToolModifier.class, );
    }

}


