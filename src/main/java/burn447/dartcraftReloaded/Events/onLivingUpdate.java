package burn447.dartcraftReloaded.Events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_SHEARABLE;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 7/6/2018.
 */
public class onLivingUpdate {

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving().hasCapability(CAPABILITY_SHEARABLE, null)){
            event.getEntityLiving().getCapability(CAPABILITY_SHEARABLE, null).update();
        }

        if(event.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player = ((EntityPlayer) event.getEntityLiving());
            Iterable<ItemStack> armor = player.getArmorInventoryList();
            for(ItemStack slotSelected : armor){
                if(slotSelected.getItem() instanceof burn447.dartcraftReloaded.Items.ItemArmor){
                    //Camo
                    if(slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasCamo()){
                        PotionEffect invisibility = new PotionEffect(MobEffects.INVISIBILITY, 20);
                        player.addPotionEffect(invisibility);
                    }
                    //Speed
                    if(slotSelected.getCapability(CAPABILITY_TOOLMOD, null).hasSpeed()){
                        PotionEffect speed = new PotionEffect(MobEffects.SPEED, 20);
                        player.addPotionEffect(speed);
                    }
                }
            }
        }
    }
}
