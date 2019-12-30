package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.Items.ItemBase;
import burn447.dartcraftReloaded.Items.ItemBottledWither;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.Items.NonBurnable.EntityNonBurnableItem;
import burn447.dartcraftReloaded.Items.NonBurnable.ItemInertCore;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_FORCEROD;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_ENDER;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_HEALING;

/**
 * Created by BURN447 on 2/23/2018.
 */
public class ItemForceRod extends ItemBase {

    public List<References.MODIFIERS> applicableModifers = new ArrayList<>();

    public ItemForceRod(String name){
        super(name);
        setHasSubtypes(true);
        setTranslationKey(name);
        setApplicableModifers();
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos.offset(facing)).getBlock().equals(Blocks.FIRE)) {
                worldIn.setBlockToAir(pos.offset(facing));
                List<Entity> list = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())));
                boolean bw = false;
                for (Entity i: list) {
                    if(i instanceof EntityItem) {
                        if(((EntityItem) i).getItem().getItem() instanceof ItemInertCore) {
                            EntityItem bottledWither = new EntityNonBurnableItem(worldIn, pos.getX(), pos.getY()  + 1, pos.getZ(), new ItemStack(ModItems.bottledWither, ((EntityItem) i).getItem().getCount()));
                            worldIn.spawnEntity(bottledWither);
                            player.getHeldItem(hand).damageItem(1, player);
                            bw = true;
                        }
                    }
                }
                if(bw) {
                    for(Entity i: list) {
                        if(i instanceof EntityItem) {
                            if (((EntityItem) i).getItem().getItem() instanceof ItemInertCore) {
                                worldIn.removeEntity(i);
                            }
                        }
                    }
                }
            }
            else {
                List<Entity> list = worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())));
                //If it is a subset of items, it will drop swap an item
                for(Entity i: list) {
                    if (i instanceof EntityItem) {
                        //Armor
                        if(((EntityItem) i).getItem().getItem() instanceof ItemArmor) {
                            if (((ItemArmor) ((EntityItem) i).getItem().getItem()).armorType == EntityEquipmentSlot.CHEST) {
                                if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.IRON) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.IRON_INGOT, 6)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                                else if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.GOLD_INGOT, 6)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                            }
                            if (((ItemArmor) ((EntityItem) i).getItem().getItem()).armorType == EntityEquipmentSlot.LEGS) {
                                if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.IRON) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.IRON_INGOT, 5)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                                else if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.GOLD_INGOT, 5)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                            }
                            if (((ItemArmor) ((EntityItem) i).getItem().getItem()).armorType == EntityEquipmentSlot.FEET) {
                                if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.IRON) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.IRON_INGOT, 3)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                                else if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.GOLD_INGOT, 3)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                            }
                            if (((ItemArmor) ((EntityItem) i).getItem().getItem()).armorType == EntityEquipmentSlot.HEAD) {
                                if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.IRON) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.IRON_INGOT, 4)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                                else if (((ItemArmor) ((EntityItem) i).getItem().getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.GOLD) {
                                    worldIn.removeEntity(i);
                                    worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.GOLD_INGOT, 4)));
                                    player.getHeldItem(hand).damageItem(1, player);
                                }
                            }
                        }
                    }
                }
            }
        }

        ItemStack stack = player.getHeldItem(hand);

        if(stack.getCapability(CAPABILITY_FORCEROD, null).getHomeLocation() != null){
            stack.getCapability(CAPABILITY_FORCEROD, null).teleportPlayerToLocation(player, stack.getCapability(CAPABILITY_FORCEROD, null).getHomeLocation());
        }

        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack stack = playerIn.getHeldItem(handIn);
        PotionEffect regenOne = new PotionEffect(MobEffects.REGENERATION, 100, 0);
        PotionEffect regenTwo = new PotionEffect(MobEffects.REGENERATION, 100, 1);
        PotionEffect regenThree = new PotionEffect(MobEffects.REGENERATION, 100, 2);

        PotionEffect invisibility = new PotionEffect(MobEffects.INVISIBILITY, 1000, 0);

        PotionEffect nightVision = new PotionEffect(MobEffects.NIGHT_VISION, 1000, 0);

        PotionEffect sight = new PotionEffect(MobEffects.GLOWING, 1000, 0);

        if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(3)){
            playerIn.addPotionEffect(regenThree);
        }
        else if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(2)){
            playerIn.addPotionEffect(regenTwo);
        }
        else if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(1)){
            playerIn.addPotionEffect(regenOne);
        }

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasCamoModifier())
            playerIn.addPotionEffect(invisibility);

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasEnderModifier()){
            if(stack.getCapability(CAPABILITY_FORCEROD, null).getHomeLocation() == null){
                stack.getCapability(CAPABILITY_FORCEROD, null).setHomeLocation(playerIn.getPosition());
            }
            else{
                stack.getCapability(CAPABILITY_FORCEROD, null).teleportPlayerToLocation(playerIn, stack.getCapability(CAPABILITY_FORCEROD, null).getHomeLocation());
            }
        }

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasSightModifier()){
            playerIn.addPotionEffect(nightVision);
        }

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasLight()) {
            playerIn.addPotionEffect(sight);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_FORCEROD, null))
            return new ForceRodProvider(CAPABILITY_FORCEROD, null);
        else
            return null;
    }

    public void setApplicableModifers(){
        applicableModifers.add(MOD_HEALING);
        applicableModifers.add(MOD_ENDER);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(3))
            tooltip.add("Healing III");
        else if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(2))
            tooltip.add("Healing II");
        else if(stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(1))
            tooltip.add("Healing I");

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasCamoModifier())
            tooltip.add("Camo");

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasEnderModifier())
            tooltip.add("Ender");

        if(stack.getCapability(CAPABILITY_FORCEROD, null).hasSightModifier())
            tooltip.add("Sight");
    }
}