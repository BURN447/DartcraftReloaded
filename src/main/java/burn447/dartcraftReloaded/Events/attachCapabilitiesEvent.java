package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneProvider;
import burn447.dartcraftReloaded.capablilities.PlayerModifier.PlayerModifierProvider;
import burn447.dartcraftReloaded.capablilities.Shearable.ShearableProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.*;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class attachCapabilitiesEvent {

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityEnderman && !event.getObject().hasCapability(CAPABILITY_BANE, null)){
                event.addCapability(BANE_CAP, new BaneProvider(CAPABILITY_BANE, null));
        }

        if(event.getObject() instanceof EntityPlayer && !event.getObject().hasCapability(CAPABILITY_PLAYERMOD, null)){
            event.addCapability(PLAYER_CAP, new PlayerModifierProvider(CAPABILITY_PLAYERMOD, null));
        }

        if(event.getObject() instanceof EntityCow || event.getObject() instanceof EntityChicken && !event.getObject().hasCapability(CAPABILITY_SHEARABLE, null)){
            event.addCapability(SHEAR_CAP, new ShearableProvider(CAPABILITY_SHEARABLE, null));
        }
    }
}
