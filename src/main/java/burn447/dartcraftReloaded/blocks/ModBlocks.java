package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ModBlocks {

    public static BlockForceOre orePower = new BlockForceOre("orePower").setCreativeTab(dartcraftReloaded.creativeTab);
    public static BlockForceSapling forceSapling = new BlockForceSapling(("forceSapling"));
    public static BlockForceLog forceLog = new BlockForceLog("forceLog").setCreativeTab(dartcraftReloaded.creativeTab);
    public static BlockForceLeaves forceLeaves = new BlockForceLeaves("forceLeaves");
    public static BlockInfuser infuser = new BlockInfuser("infuser");
    public static BlockFluidForce blockFluidForce = new BlockFluidForce();
    public static BlockForceBrick forceBrick = new BlockForceBrick();
    public static BlockForceFurnace forceFurnace = new BlockForceFurnace(false);
    public static BlockBase forcePlanks = new BlockBase(Material.WOOD, "forcePlanks").setCreativeTab(dartcraftReloaded.creativeTab);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                orePower,
                forceSapling,
                forceLog,
                forceLeaves,
                infuser,
                blockFluidForce,
                forceFurnace,
                forcePlanks
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
                forcePlanks.createItemBlock()
        );

    }

    public static void registerModels() {
        orePower.registerItemModel(Item.getItemFromBlock(orePower));
        forceLog.registerItemModel(Item.getItemFromBlock(forceLog));
        infuser.registerItemModel(Item.getItemFromBlock(infuser));
        forceFurnace.registerItemModel(Item.getItemFromBlock(forceFurnace));
        forcePlanks.registerItemModel(Item.getItemFromBlock(forcePlanks));
        forceSapling.registerItemModel(Item.getItemFromBlock(forceSapling));
    }

    public static void registerNames() {
        forcePlanks.setRegistryName("forcePlanks");
        forcePlanks.setUnlocalizedName("forcePlanks");
    }
}
