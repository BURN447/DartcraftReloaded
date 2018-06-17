package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.BANE_CAP;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_BANE;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class attachCapabilitiesEvent {

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityEnderman && !event.getObject().hasCapability(CAPABILITY_BANE, null)){
            System.out.println("Attatching Capability to Enderman");
            event.addCapability(BANE_CAP, new BaneProvider(CAPABILITY_BANE, null));
        }
    }
}
