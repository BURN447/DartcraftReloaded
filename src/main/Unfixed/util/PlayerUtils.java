package burn447.dartcraftReloaded.util;

import burn447.dartcraftReloaded.config.ConfigHandler;
import net.minecraft.util.text.TextComponentString;
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
            event.player.sendMessage(new TextComponentString("This version is officially EOL for 1.12.2. The mod is in no way complete, and is not recommended to be used in survival worlds. However, there will no longer be updates released for 1.12.2, which will allow a certain measure of stability. Please move to the 1.15 port when it becomes available on Curseforge"));

        }
        if(event.player.getName().equals("BURN447")) {
            event.player.sendMessage(new TextComponentString("Welcome BURN."));
        }
    }
}
