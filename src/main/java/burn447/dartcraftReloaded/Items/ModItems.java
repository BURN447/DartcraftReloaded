package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.Items.Tools.*;
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
    public static ItemBaseFood cookieFortune = new ItemBaseFood("cookie_fortune", 2, 0.1f, false);
    public static ItemBaseFood soulWafer = new ItemBaseFood("soul_wafer", 2, 1, false);
    public static ItemArmor forceHelmet = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.HEAD, "force_helmet");
    public static ItemArmor forceChest = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.CHEST, "force_chest");
    public static ItemArmor forceLegs = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.LEGS, "force_legs");
    public static ItemArmor forceBoots = new ItemArmor(dartcraftReloaded.forceArmorMaterial, EntityEquipmentSlot.FEET, "force_boots");
    public static ItemForceRod forceRod = new ItemForceRod("force_rod");
    public static ItemForceWrench forceWrench = new ItemForceWrench("force_wrench");
    public static ItemBase goldenPowerSource = new ItemBase("goldenPowerSource").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBase claw = new ItemBase("forceClaw").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemFortune fortune = new ItemFortune("fortune");
    public static ItemBase forceGear = new ItemBase("gear_force").setCreativeTab(dartcraftReloaded.creativeTab);
    public static ItemBase snowCookie = new ItemBase("cookie_snow").setCreativeTab(dartcraftReloaded.creativeTab);

    //Tools
    public static ItemForcePickaxe forcePickaxe = new ItemForcePickaxe("forcePickaxe");
    public static ItemForceAxe forceAxe = new ItemForceAxe("forceAxe");
    public static ItemForceSword forceSword = new ItemForceSword("forceSword");
    public static ItemForceShovel forceShovel = new ItemForceShovel("forceSpade");
    public static ItemForceShears forceShears = new ItemForceShears("forceShears");
    public static ItemForceMitt forceMitt = new ItemForceMitt("forceMitt");

    //Experience Tome
    public static ItemExperienceTome experienceTome = new ItemExperienceTome("experienceTome");

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
                forceRod,
                forceWrench,
                forcePickaxe,
                forceAxe,
                forceSword,
                forceShovel,
                goldenPowerSource,
                claw,
                fortune,
                forceShears,
                experienceTome,
                forceGear,
                snowCookie
                //forceMitt
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
        forceWrench.registerItemModel();
        forcePickaxe.registerItemModel();
        forceAxe.registerItemModel();
        forceSword.registerItemModel();
        forceShovel.registerItemModel();
        goldenPowerSource.registerItemModel();
        claw.registerItemModel();
        fortune.registerItemModel();
        forceShears.registerItemModel();
        experienceTome.registerItemModel();
        forceGear.registerItemModel();
        snowCookie.registerItemModel();
        //forceMitt.registerItemModel();
    }
}
