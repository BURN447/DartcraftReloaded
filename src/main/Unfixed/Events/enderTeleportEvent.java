package burn447.dartcraftReloaded.Events;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_BANE;

/**
 * Created by BURN447 on 6/17/2018.
 */
public class enderTeleportEvent {

    @SubscribeEvent
    public void onEnderTeleportEvent(EnderTeleportEvent event){
        if(event.getEntity() instanceof EntityEnderman){
            EntityEnderman enderman = ((EntityEnderman) event.getEntity());
            if(!enderman.getCapability(CAPABILITY_BANE, null).canTeleport()){
                event.setCanceled(true);
            }
        }
    }
}
