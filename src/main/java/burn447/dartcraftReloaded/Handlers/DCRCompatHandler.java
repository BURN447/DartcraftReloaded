package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.compat.TOPCompat;
import net.minecraftforge.fml.common.Loader;

public class DCRCompatHandler {
    public static void registerTOP() {
        if(Loader.isModLoaded("theoneprobe")){
            TOPCompat.register();
        }
    }
}
