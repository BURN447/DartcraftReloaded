package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Events.onHarvestEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by BURN447 on 6/1/2018.
 */
public class DCREventHandler {

    public static void init(){
        MinecraftForge.EVENT_BUS.register(new onHarvestEvent());
    }


}
