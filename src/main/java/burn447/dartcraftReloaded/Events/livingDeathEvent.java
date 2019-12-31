package burn447.dartcraftReloaded.Events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_PLAYERMOD;

/**
 * Created by BURN447 on 8/6/2018.
 */
public class livingDeathEvent {

    @SubscribeEvent
    public void livingDeathEvent(LivingDeathEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            
        }

    }
}
