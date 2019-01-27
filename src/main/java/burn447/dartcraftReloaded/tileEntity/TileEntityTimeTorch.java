package burn447.dartcraftReloaded.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

import javax.annotation.Nullable;
import java.util.Random;

//All Code Heavily inspired by Torcherino. Credit to Moze_Intel, Sci4me and NinjaPhenix
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

    private Random rand;

    public TileEntityTimeTorch() {
        this.speed = 100;
        this.rand = new Random();
        System.out.println("TIME TORCH MADE");
    }

    @Override
    public void update() {
        if(this.world.isRemote) return;
        this.updateCachedMode();
        this.tickNeighbor();
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

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setByte("Speed", this.speed);
        nbt.setByte("Mode", this.mode);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.speed = nbt.getByte("Speed");
        this.mode = nbt.getByte("Mode");
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new SPacketUpdateTileEntity(getPos(), -999, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
