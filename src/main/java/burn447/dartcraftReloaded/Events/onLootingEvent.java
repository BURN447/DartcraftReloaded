package burn447.dartcraftReloaded.Events;

import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_PLAYERMOD;

public class onLootingEvent {

    @SubscribeEvent
    public void onLooting(LootingLevelEvent event) {
        int level = event.getLootingLevel();

        if(event.getDamageSource().getTrueSource().hasCapability(CAPABILITY_PLAYERMOD, null)) {
            level += event.getDamageSource().getTrueSource().getCapability(CAPABILITY_PLAYERMOD, null).getLuckLevel();
        }

        event.setLootingLevel(level);
    }
}
