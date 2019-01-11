package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.Items.ItemBase;
import burn447.dartcraftReloaded.dartcraftReloaded;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemForceMitt extends ItemBase {

    private final float attackDamage;
    ToolMaterial material = dartcraftReloaded.forceToolMaterial;

    public ItemForceMitt(String name) {
        super(name);
        this.setHarvestLevel("axe", 2);
        this.setHarvestLevel("pickaxe", 2);
        this.setHarvestLevel("shovel", 2);
        this.attackDamage = 3.0F;
        this.setMaxDamage(1000);

    }

    @Override
    @SuppressWarnings("incomplete-switch")
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }

    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }

    public float getAttackDamage()
    {
        return this.material.getAttackDamage();
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }


}
