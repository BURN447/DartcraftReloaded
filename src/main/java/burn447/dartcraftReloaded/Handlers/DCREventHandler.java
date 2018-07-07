package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Events.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by BURN447 on 6/1/2018.
 */
public class DCREventHandler {

    public static void init(){
        MinecraftForge.EVENT_BUS.register(new onHarvestEvent());
        MinecraftForge.EVENT_BUS.register(new attachCapabilitiesEvent());
        MinecraftForge.EVENT_BUS.register(new enderTeleportEvent());
        MinecraftForge.EVENT_BUS.register(new attackEntityEvent());
        MinecraftForge.EVENT_BUS.register(new onLivingUpdate());
    }


}
