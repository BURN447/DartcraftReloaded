package burn447.dartcraftReloaded.util;

import burn447.dartcraftReloaded.config.ConfigHandler;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by BURN447 on 7/23/2018.
 */
public class PlayerUtils {

    @SubscribeEvent
    //@SideOnly(Side.CLIENT)
    public void onLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if(ConfigHandler.betaMessage) {
            event.player.sendMessage(new TextComponentString("Thank you for playing a BETA of Dartcraft Reloaded. However, since this is BETA, there could be many bugs that have not yet been found. " +
                    "If you find one of these, please report it to the Bug Tracker, where I can fix it withing the near future. The bug tracker can be found on the DCR Github.\n Thanks! \nBURN"));

        }
        if(event.player.getName().equals("BURN447")) {
            event.player.sendMessage(new TextComponentString("Welcome BURN."));
        }
    }
}
