package burn447.dartcraftReloaded.Blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.tileEntity.TileEntityForceFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ModBlocks {

    public static BlockForceOre orePower = new BlockForceOre("orePower").setCreativeTab(dartcraftReloaded.creativeTab);
    public static BlockForceSapling forceSapling = new BlockForceSapling(("forceSapling"));
    public static BlockForceLog forceLog = new BlockForceLog("forceLog").setCreativeTab(dartcraftReloaded.creativeTab);
    public static BlockForceLeaves forceLeaves = new BlockForceLeaves("forceLeaves");
    public static BlockForceFurnace forceFurnace = new BlockForceFurnace("forceFurnace", false);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                orePower,
                forceSapling,
                forceLog,
                forceLeaves
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                orePower.createItemBlock(),
                forceSapling.createItemBlock(),
                forceLog.createItemBlock(),
                forceLeaves.createItemBlock()
        );

    }

    public static void registerModels() {
        orePower.registerItemModel(Item.getItemFromBlock(orePower));
        forceLog.registerItemModel(Item.getItemFromBlock(forceLog));
    }
}
