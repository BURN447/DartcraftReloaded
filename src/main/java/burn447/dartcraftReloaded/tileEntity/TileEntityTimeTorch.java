package burn447.dartcraftReloaded.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.BlockFluidBase;

//All Code Heavily inspired by Torcherino. Credit to Moze_Inte, Sci4me and NinjaPhenix
public class TileEntityTimeTorch extends TileEntity implements ITickable {

    private int xMin;
    private int yMin;
    private int zMin;
    private int xMax;
    private int yMax;
    private int zMax;

    private byte mode;
    private byte cachedMode;

    private byte speed;

    public TileEntityTimeTorch() {
        this.speed = 3;
    }

    @Override
    public void update() {

    }

    protected int speed(int base) { return base; }

    private void tickNeighbor() {
        for(int x = this.xMin; x <= this.xMax; x++)
        {
            for(int y = this.yMin; y <= this.yMax; y++)
            {
                for(int z = this.zMin; z <= this.zMax; z++)
                {
                    this.tickBlock(new BlockPos(x, y, z));
                }
            }
        }
    }

    private void updateCachedMode() {
        if(cachedMode != mode) {
            this.xMin = this.pos.getX() - this.mode;
            this.yMin = this.pos.getY() - 1;
            this.zMin = this.pos.getZ() - this.mode;
            this.xMax = this.pos.getX() + this.mode;
            this.yMax = this.pos.getY() + 1;
            this.zMax = this.pos.getZ() + this.mode;
            this.cachedMode = this.mode;
        }
    }

    private void tickBlock(BlockPos pos) {
        IBlockState blockState = this.world.getBlockState(pos);
        Block block = blockState.getBlock();

        if(block == null || block instanceof BlockFluidBase)
            return;

        if(block.getTickRandomly()) {
            for(int i = 0; i < this.speed(this.speed); i++)
            {
                if(getWorld().getBlockState(pos) != blockState) break;
                block.updateTick(this.world, pos, blockState, this.rand);
            }
        }
        if(block.hasTileEntity(blockState)) {
            TileEntity tile = this.world.getTileEntity(pos);

            if(tile == null || tile.isInvalid()) return;

            for(int i = 0; i < this.speed(this.speed); i++)
            {
                if(tile.isInvalid())
                {
                    break;
                }
                if(tile instanceof ITickable)
                {
                    ((ITickable) tile).update();
                }
            }
        }
    }
}
