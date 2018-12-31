package burn447.dartcraftReloaded.util;

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
        if(event.player.getName().equals("BURN447")) {
            event.player.sendMessage(new TextComponentString("Welcome BURN."));
        }
        else if(event.player.getName().equals("Darkosto")){
            event.player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Happy Birthday Darkosto!"));
        }
        else if(event.player.getName().equals("Bacon_Donut")){
            event.player.sendMessage(new TextComponentString(TextFormatting.RED + "May Bacon rain from the sky!"));
        }
    }
}
