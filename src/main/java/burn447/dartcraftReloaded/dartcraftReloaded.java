package burn447.dartcraftReloaded;

import burn447.dartcraftReloaded.blocks.ModBlocks;
import burn447.dartcraftReloaded.Fluids.ModFluids;
import burn447.dartcraftReloaded.Handlers.*;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.client.tabDartcraft;
import burn447.dartcraftReloaded.proxy.CommonProxy;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.world.DCRWorldGen;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static burn447.dartcraftReloaded.util.References.modId;

/**
 * Created by BURN447 on 2/4/2018.
 */

@Mod(modid = modId, name = References.name, version = References.version)
public class dartcraftReloaded {

    public static final tabDartcraft creativeTab = new tabDartcraft();

    @Mod.Instance(modId)
    public static dartcraftReloaded instance;

    @SidedProxy(serverSide = "burn447.dartcraftreloaded.proxy.CommonProxy", clientSide = "burn447.dartcraftReloaded.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final ItemArmor.ArmorMaterial forceArmorMaterial = EnumHelper.addArmorMaterial("FORCE", modId + ":force", 15, new int[]{1, 2, 3, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    public static final ItemTool.ToolMaterial forceToolMaterial = EnumHelper.addToolMaterial("FORCE", 3, 1561, 8.0F, 8.0F, 22);

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        System.out.println("Dartcraft Reloaded Pre-Init");
        GameRegistry.registerWorldGenerator(new DCRWorldGen(), 3);
        proxy.registerTileEntities();
        proxy.preInit();
        DCRPotionHandler.preInit(e);
        ModFluids.registerFluids();
        ModFluids.setUpFluids();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        System.out.println("Dartcraft Reloaded Init");
        NetworkRegistry.INSTANCE.registerGuiHandler(dartcraftReloaded.instance, new DCRGUIHandler());
        GameRegistry.registerFuelHandler(new DCRFuelHandler());
        DCREventHandler.init();
        DCRPacketHandler.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        System.out.println("Dartcraft Reloaded Post-Init");
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event){
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.registerNames();
            ModBlocks.register(event.getRegistry());
            ModBlocks.registerModels();
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }

        @SubscribeEvent
        public static void registerPotions(RegistryEvent.Register<Potion> event) {
            DCRPotionHandler.registerPotions(event.getRegistry());
        }
    }

}

