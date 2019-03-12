package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.util.MobUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/19/2018.
 */
public class attackEntityEvent {

    @SubscribeEvent
    public void AttackEntityEvent(AttackEntityEvent event) {

        int bleedLevel = 0, heatLevel = 0, damageLevel = 0;
        //Check for Player Attacking Entity
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = event.getEntityPlayer();
            Iterable<ItemStack> armor = player.getArmorInventoryList();
            //Iterate through Armor
            if (player.getHeldItemMainhand() == ItemStack.EMPTY) {
                for (ItemStack slot : armor) {
                    //Only need one Level of Bane
                    if (slot.hasCapability(CAPABILITY_TOOLMOD, null)) {

                    }
                }


                if (event.getTarget() instanceof EntityLivingBase) {
                    //Bleeding
                    MobUtil.addBleedingEffect(bleedLevel, (EntityLivingBase) event.getTarget());

                    //Heat
                    event.getTarget().setFire(heatLevel * 10);

                    //Damage
                    event.getTarget().attackEntityFrom(DamageSource.GENERIC, damageLevel);
                }
            }
        }
    }
}
