package burn447.dartcraftReloaded.config;


import burn447.dartcraftReloaded.util.References;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = References.modId)
public class ConfigHandler {

    @Config.Comment("Enable Time Torch. Default: true")
    public static boolean timetorchEnabled = true;

    @Config.Comment("Print in Log when Time Torch is placed and by who. Default: false")
    public static boolean timeTorchLogging = false;


    @Mod.EventBusSubscriber(modid = References.modId)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(References.modId)) {
                ConfigManager.sync(References.modId, Config.Type.INSTANCE);
            }
        }
    }
}


