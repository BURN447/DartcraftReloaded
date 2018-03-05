package burn447.dartcraftReloaded.Blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by BURN447 on 3/4/2018.
 */
public interface TileEntityProvider<TE extends TileEntity> {

    Class<TE> getTileEntityClass();

    default TE createTileEntity() {
        try {
            return getTileEntityClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    default TE getTileEntity(IBlockAccess world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            if (getTileEntityClass().isAssignableFrom(te.getClass())) {
                return (TE)te;
            } else {
                throw new IllegalArgumentException(String.format("Invalid TileEntity type at %s, expected %s got %s", pos, getTileEntityClass().getName(), te.getClass().getName()));
            }
        } else {
            throw new IllegalArgumentException(String.format("No TileEntity at position %s", pos));
        }
    }

}