package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Potion.PotionBleeding;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 6/13/2018.
 */
public class DCRPotionHandler {

    public static Potion potionBleeding;

    public static void preInit(FMLPreInitializationEvent event){
        potionBleeding = new PotionBleeding();
    }

    public static void registerPotions(IForgeRegistry<Potion> registry){
        registry.register(potionBleeding);
    }
}
