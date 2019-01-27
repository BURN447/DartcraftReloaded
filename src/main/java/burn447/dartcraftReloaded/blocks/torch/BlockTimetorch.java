package burn447.dartcraftReloaded.blocks.torch;

import burn447.dartcraftReloaded.tileEntity.TileEntityTimeTorch;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTimetorch extends BlockForceTorch {

    public BlockTimetorch(String name) {
        super(name);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTimeTorch();
    }
}
