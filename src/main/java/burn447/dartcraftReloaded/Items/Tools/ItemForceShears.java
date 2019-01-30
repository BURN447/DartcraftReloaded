package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_SHEARABLE;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/9/2018.
 */
public class ItemForceShears extends ItemShears {

    String name;

    public ItemForceShears(String name){
        this.setRegistryName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.setTranslationKey(name);
        this.name = name;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {

        if(itemstack.getCapability(CAPABILITY_TOOLMOD, null).hasRainbow()) {
            if (entity.world.isRemote) {
                return false;
            }
            if (entity instanceof net.minecraftforge.common.IShearable) {
                net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable) entity;
                BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
                if (target.isShearable(itemstack, entity.world, pos)) {
                    java.util.List<ItemStack> drops = target.onSheared(itemstack, entity.world, pos, net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, itemstack));

                    //Random Drop Color
                    Random random = new Random();
                    int color = random.nextInt(16);
                    ItemStack oldDrops = drops.get(0);
                    int numItems = oldDrops.getCount();
                    ItemStack newDrops = new ItemStack(Item.getItemFromBlock(Blocks.WOOL), numItems, color);
                    drops.remove(0);
                    drops.add(newDrops);

                    java.util.Random rand = new java.util.Random();
                    dropItems(entity, drops, rand);
                    itemstack.damageItem(1, entity);
                }
                return true;
            }
            return false;
        }

        if(entity instanceof EntityCow) {
            if (entity.hasCapability(CAPABILITY_SHEARABLE, null)) {
                if(entity.getCapability(CAPABILITY_SHEARABLE, null).canBeSheared()) {

                    if (entity.world.isRemote) {
                        return false;
                    }

                    java.util.Random rand = new java.util.Random();
                    int i = 1 + rand.nextInt(3);

                    java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
                    for (int j = 0; j < i; ++j)
                        ret.add(new ItemStack(Items.LEATHER, 1));

                    entity.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
                    dropItems(entity, ret, rand);
                    entity.getCapability(CAPABILITY_SHEARABLE, null).setSheared(true);
                    return true;
                }
            }
        }
        if(entity instanceof EntityChicken) {
            if (entity.hasCapability(CAPABILITY_SHEARABLE, null)) {
                if(entity.getCapability(CAPABILITY_SHEARABLE, null).canBeSheared()) {

                    if (entity.world.isRemote) {
                        return false;
                    }

                    java.util.Random rand = new java.util.Random();
                    int i = 1 + rand.nextInt(3);

                    java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
                    for (int j = 0; j < i; ++j)
                        ret.add(new ItemStack(Items.FEATHER, 1));

                    entity.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
                    dropItems(entity, ret, rand);
                    entity.getCapability(CAPABILITY_SHEARABLE, null).setSheared(true);
                    return true;
                }
            }
        }
        return super.itemInteractionForEntity(itemstack, player, entity, hand);
    }

    private void dropItems(EntityLivingBase entity, List<ItemStack> drops, Random rand) {
        for (ItemStack stack : drops) {
            net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(stack, 1.0F);
            ent.motionY += rand.nextFloat() * 0.05F;
            ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasRainbow()){
            tooltip.add("Rainbow");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
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
