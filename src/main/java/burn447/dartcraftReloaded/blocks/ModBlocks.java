package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ModBlocks {

    public static BlockForceOre orePower = new BlockForceOre("orePower").setCreativeTab(dartcraftReloaded.creativeTab);
    public static BlockForceSapling forceSapling = new BlockForceSapling();
    public static BlockForceLog forceLog = new BlockForceLog("forceLog", "logWood");
    public static BlockForceLeaves forceLeaves = new BlockForceLeaves("forceLeaves");
    public static BlockInfuser infuser = new BlockInfuser("infuser");
    public static BlockFluidForce blockFluidForce = new BlockFluidForce();
    public static BlockForceFurnace forceFurnace = new BlockForceFurnace(false, "forceFurnace");
    public static BlockForceFurnace LIT_FORCEFURNACE = new BlockForceFurnace(true, "litforceFurnace");
    public static BlockBase forcePlanks = new BlockBase(Material.WOOD, "forcePlanks", "plankWood", SoundType.WOOD).setCreativeTab(dartcraftReloaded.creativeTab);

    //Bricks
    public static BlockForceBrick forceBrickRed = new BlockForceBrick("forceBrickRed");
    public static BlockForceBrick forceBrickYellow = new BlockForceBrick("forceBrickYellow");
    public static BlockForceBrick forceBrickGreen = new BlockForceBrick("forceBrickGreen");
    public static BlockForceBrick forceBrickBlue = new BlockForceBrick("forceBrickBlue");
    public static BlockForceBrick forceBrickWhite = new BlockForceBrick("forceBrickWhite");
    public static BlockForceBrick forceBrickBlack = new BlockForceBrick("forceBrickBlack");
    public static BlockForceBrick forceBrickBrown = new BlockForceBrick("forceBrickBrown");
    public static BlockForceBrick forceBrickOrange = new BlockForceBrick("forceBrickOrange");
    public static BlockForceBrick forceBrickLightBlue = new BlockForceBrick("forceBrickLightBlue");
    public static BlockForceBrick forceBrickMagenta = new BlockForceBrick("forceBrickMagenta");
    public static BlockForceBrick forceBrickPink = new BlockForceBrick("forceBrickPink");
    public static BlockForceBrick forceBrickLightGray = new BlockForceBrick("forceBrickLightGray");
    public static BlockForceBrick forceBrickLime = new BlockForceBrick("forceBrickLime");
    public static BlockForceBrick forceBrickCyan = new BlockForceBrick("forceBrickCyan");
    public static BlockForceBrick forceBrickPurple = new BlockForceBrick("forceBrickPurple");
    public static BlockForceBrick forceBrickGray = new BlockForceBrick("forceBrickGray");

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                orePower,
                forceSapling,
                forceLog,
                forceLeaves,
                infuser,
                blockFluidForce,
                forceFurnace,
                forcePlanks,
                LIT_FORCEFURNACE,
                forceBrickRed,
                forceBrickYellow,
                forceBrickGreen,
                forceBrickBlue,
                forceBrickWhite,
                forceBrickBlack,
                forceBrickBrown,
                forceBrickOrange,
                forceBrickLightBlue,
                forceBrickMagenta,
                forceBrickPink,
                forceBrickLightGray,
                forceBrickLime,
                forceBrickCyan,
                forceBrickPurple,
                forceBrickGray
        );

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                orePower.createItemBlock(),
                forceSapling.createItemBlock(),
                forceLog.createItemBlock(),
                forceLeaves.createItemBlock(),
                infuser.createItemBlock(),
                forceFurnace.createItemBlock(),
                forcePlanks.createItemBlock(),
                forceBrickRed.createItemBlock(),
                forceBrickYellow.createItemBlock(),
                forceBrickGreen.createItemBlock(),
                forceBrickBlue.createItemBlock(),
                forceBrickWhite.createItemBlock(),
                forceBrickBlack.createItemBlock(),
                forceBrickBrown.createItemBlock(),
                forceBrickOrange.createItemBlock(),
                forceBrickLightBlue.createItemBlock(),
                forceBrickLime.createItemBlock(),
                forceBrickCyan.createItemBlock(),
                forceBrickPurple.createItemBlock(),
                forceBrickGray.createItemBlock()
        );

    }

    public static void registerModels() {
        orePower.registerItemModel(Item.getItemFromBlock(orePower));
        forceLog.registerItemModel(Item.getItemFromBlock(forceLog));
        infuser.registerItemModel(Item.getItemFromBlock(infuser));
        forceFurnace.registerItemModel(Item.getItemFromBlock(forceFurnace));
        forcePlanks.registerItemModel(Item.getItemFromBlock(forcePlanks));
        forceSapling.registerItemModel(Item.getItemFromBlock(forceSapling));
        forceLeaves.registerItemModel(Item.getItemFromBlock(forceLeaves));
        LIT_FORCEFURNACE.registerItemModel(Item.getItemFromBlock(LIT_FORCEFURNACE));
        forceBrickWhite.registerItemModel(Item.getItemFromBlock(forceBrickWhite));
    }

    public static void registerNames() {
        forcePlanks.setRegistryName("forcePlanks");
        forcePlanks.setTranslationKey("forcePlanks");
    }

    public static void registerOreDict() {
        forcePlanks.initOreDict();
        //forceLog.initOreDict();
    }
}
