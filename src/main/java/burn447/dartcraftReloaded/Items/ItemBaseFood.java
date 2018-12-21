package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by BURN447 on 2/19/2018.
 */
public class ItemBaseFood extends ItemFood {

    protected String name;

    public ItemBaseFood(String name, int amount, float saturation, boolean isWolfFood){
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        if(name == "cookie_fortune"){
            this.setAlwaysEdible();
            this.maxStackSize = 1;
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
            if(name == "cookie_fortune"){
                entityplayer.addItemStackToInventory(new ItemStack(ModItems.fortune));
            }
            if(name == "soul_wafer"){
                this.randPotionEffect(entityplayer);
            }

        }
        stack.shrink(1);
        return stack;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBaseFood setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public ItemBaseFood randPotionEffect(EntityLivingBase entityLiving){
        Random rnd = new Random();
        int rand = rnd.nextInt(16);
        EntityPlayer entityplayer = (EntityPlayer)entityLiving;

        switch(rand){
            case 0:
                //Speed
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 1000));
                break;
            case 1:
                //Haste
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 1000));
                break;
            case 2:
                //Strength
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 1000));
                break;
            case 3:
                //Jump Boost
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 1000));
                break;
            case 4:
                //Regeneration
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 1000));
                break;
            case 5:
                //Resistance
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 1000));
                break;
            case 6:
                //Fire Resistance
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 1000));
                break;
            case 7:
                //Water Breathing
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 1000));
                break;
            case 8:
                //Invisibility
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 1000));
                break;
            case 9:
                //Night Vision
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 1000));
                break;
            case 10:
                //Health Boost
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(21), 1000));
                break;
            case 11:
                //Absorption
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 1000));
                break;
            case 12:
                //Saturation
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(23), 1000));
                break;
            case 13:
                //Glowing
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 1000));
                break;
            case 14:
                //Levitation
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(25), 1000));
                break;
            case 15:
                //Luck
                entityplayer.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 1000));
                break;
        }
        return this;
    }

}
