package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.tileEntity.TileEntityForceFurnace;
import net.minecraft.block.BlockFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockForceFurnace extends BlockFurnace {

    protected BlockForceFurnace(boolean isBurning) {
        super(isBurning);
        this.setRegistryName("forceFurnace");
        this.setUnlocalizedName("forceFurnace");
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, "forceFurnace");
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityForceFurnace();
    }
}
