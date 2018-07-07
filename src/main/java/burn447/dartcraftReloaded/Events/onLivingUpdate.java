package burn447.dartcraftReloaded.Events;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_SHEARABLE;

/**
 * Created by BURN447 on 7/6/2018.
 */
public class onLivingUpdate {

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving().hasCapability(CAPABILITY_SHEARABLE, null)){
            event.getEntityLiving().getCapability(CAPABILITY_SHEARABLE, null).update();
        }
    }
}
