package burn447.dartcraftReloaded.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by BURN447 on 3/4/2018.
 */
public abstract class BlockTE<TE extends TileEntity> extends BlockBase implements TileEntityProvider<TE> {

    public BlockTE(Material material, String name) {
        super(material, name);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nonnull
    @Override
    public TE createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return createTileEntity();
    }

    @Override
    public abstract Class<TE> getTileEntityClass();

}