package burn447.dartcraftReloaded.util;

import burn447.dartcraftReloaded.Potion.Effects.EffectBleeding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class MobUtil {

    public static void removeCreeperExplodeAI(EntityCreeper entity){
        for(Object a : entity.tasks.taskEntries.toArray()){
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;

            if(entity instanceof EntityCreeper && (ai instanceof EntityAICreeperSwell || ai instanceof EntityAIAttackMelee)){
                entity.tasks.removeTask(ai);
                entity.setCreeperState(-1);
            }
        }

        for(Object a : entity.targetTasks.taskEntries.toArray()){
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;

            if(entity instanceof EntityCreeper && ai instanceof EntityAINearestAttackableTarget){
                entity.targetTasks.removeTask(ai);
                entity.setCreeperState(-1);
            }
        }
    }

    public static void addBleedingEffect(ItemStack stack, EntityLivingBase target){
        PotionEffect bleedingOne = new EffectBleeding(2);
        PotionEffect bleedingTwo = new EffectBleeding(4);
        PotionEffect bleedingThree = new EffectBleeding(5);
        PotionEffect bleedingFour = new EffectBleeding(16);


        if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(4)){
            target.addPotionEffect(bleedingFour);
        }

        else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(3)){
            target.addPotionEffect(bleedingThree);
        }

        else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2)){
            target.addPotionEffect(bleedingTwo);
        }

        else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1)){
            target.addPotionEffect(bleedingOne);
        }
    }

    public static void addBleedingEffect(int level, EntityLivingBase target){
        PotionEffect bleedingOne = new EffectBleeding(2);
        PotionEffect bleedingTwo = new EffectBleeding(4);
        PotionEffect bleedingThree = new EffectBleeding(5);
        PotionEffect bleedingFour = new EffectBleeding(16);


        if(level == 4){
            target.addPotionEffect(bleedingFour);
        }

        else if(level == 3){
            target.addPotionEffect(bleedingThree);
        }

        else if(level == 2){
            target.addPotionEffect(bleedingTwo);
        }

        else if(level == 1){
            target.addPotionEffect(bleedingOne);
        }
    }
}
