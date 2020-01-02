package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.Potion.Potions.PotionBleeding;
import burn447.dartcraftReloaded.Potion.Potions.PotionMagnet;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 6/13/2018.
 */
public class DCRPotionHandler {

    public static Potion potionBleeding;
    public static Potion potionMagnet;

    public static void preInit(FMLPreInitializationEvent event){
        potionBleeding = new PotionBleeding();
        potionMagnet = new PotionMagnet();
    }

    public static void registerPotions(IForgeRegistry<Potion> registry){
        registry.register(potionBleeding);
        registry.register(potionMagnet);
    }
}
