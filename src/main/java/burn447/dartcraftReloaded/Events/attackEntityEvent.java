package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.util.MobUtil;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_PLAYERMOD;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/19/2018.
 */
public class attackEntityEvent {

    @SubscribeEvent
    public void AttackEntityEvent(AttackEntityEvent event) {

        if(event.getEntity() instanceof EntityPlayer){
            EntityPlayer player = event.getEntityPlayer();

            if(player.hasCapability(CAPABILITY_PLAYERMOD, null)){

            }
        }


        if (event.getTarget() instanceof EntityCreeper) {
            System.out.println("Attacked Creeper");
            if(event.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = event.getEntityPlayer();
                Iterable<ItemStack> armor = player.getArmorInventoryList();
                for (ItemStack slot : armor) {
                    if (slot.hasCapability(CAPABILITY_TOOLMOD, null)) {
                        if (slot.getCapability(CAPABILITY_TOOLMOD, null).hasBane()) {
                            MobUtil.removeCreeperExplodeAI(((EntityCreeper) event.getTarget()));
                        }
                    }
                }
            }
        }
    }
}
