package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ModItems {

    public static ItemBase gemForceGem = new ItemBase("gem_forceGem").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBase ingotForce = new ItemBase("ingot_force").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBase nuggetForce = new ItemBase("nugget_force").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBase stickForce = new ItemBase("stick_force").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBaseFood cookieFortune = new ItemBaseFood("cookie_fortune", 2, 0.1f, false).setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBaseFood soulWafer = new ItemBaseFood("soul_wafer", 2, 1, false).setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemArmor forceHelmet = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.HEAD, "force_helmet");
    public static ItemArmor forceChest = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.CHEST, "force_chest");
    public static ItemArmor forceLegs = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.LEGS, "force_legs");
    public static ItemArmor forceBoots = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.FEET, "force_boots");
    public static ItemForceRod forceRod = new ItemForceRod("force_rod");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                gemForceGem,
                ingotForce,
                nuggetForce,
                stickForce,
                cookieFortune,
                soulWafer,
                forceHelmet,
                forceChest,
                forceLegs,
                forceBoots,
                forceRod
        );
    }

    public static void registerModels() {
        gemForceGem.registerItemModel();
        ingotForce.registerItemModel();
        nuggetForce.registerItemModel();
        stickForce.registerItemModel();
        cookieFortune.registerItemModel();
        soulWafer.registerItemModel();
        forceHelmet.registerItemModel();
        forceChest.registerItemModel();
        forceLegs.registerItemModel();
        forceBoots.registerItemModel();
        forceRod.registerItemModel();
    }
}
