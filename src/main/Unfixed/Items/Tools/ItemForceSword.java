package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
public class ItemForceSword extends ItemSword {

    private static String name;

    public ItemForceSword(String name) {
        super(dartcraftReloaded.forceToolMaterial);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }
        return true;
    }

    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.WEB;
    }

    public float getAttackDamage() {
        return dartcraftReloaded.forceToolMaterial.getAttackDamage();
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.getAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        //Wing Modifier
        ItemStack heldStack = playerIn.getHeldItem(handIn);
        if(heldStack.hasCapability(CAPABILITY_TOOLMOD, null)) {
            if(heldStack.getCapability(CAPABILITY_TOOLMOD, null).hasWing()) {
                Vec3d vec = playerIn.getLookVec();
                double wantedVelocity = 1.7;
                playerIn.motionX = vec.x * wantedVelocity;
                playerIn.motionY = vec.y * wantedVelocity;
                playerIn.motionZ = vec.z * wantedVelocity;
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.WEB)
        {
            return 15.0F;
        }
        else
        {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
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
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasWing())
            toolTip.add("Wing");
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleed())
            toolTip.add("Bleeding " + stack.getCapability(CAPABILITY_TOOLMOD, null).getBleedLevel());
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
