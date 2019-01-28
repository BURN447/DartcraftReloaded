package burn447.dartcraftReloaded.tileEntity;

import burn447.dartcraftReloaded.blocks.torch.BlockTimetorch;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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

    private byte speed;

    private Random rand;

    public TileEntityTimeTorch() {
        this.speed = 3;
        this.rand = new Random();
    }

    @Override
    public void update() {
        if(this.world.isRemote) return;
        //if(this.mode == 0 || this.speed == 0) return;
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
        this.xMin = this.pos.getX() - 1;
        this.yMin = this.pos.getY() - 1;
        this.zMin = this.pos.getZ() - 1;
        this.xMax = this.pos.getX() + 1;
        this.yMax = this.pos.getY() + 1;
        this.zMax = this.pos.getZ() + 1;
    }


    private void tickBlock(BlockPos pos) {
        IBlockState blockState = this.world.getBlockState(pos);
        Block block = blockState.getBlock();

        if(block == null || block instanceof BlockFluidBase || block instanceof BlockTimetorch || block == Blocks.AIR)
            return;

        if(block.getTickRandomly()) {
            for(int i = 0; i < this.speed; i++)
            {
                if(getWorld().getBlockState(pos) != blockState) break;
                block.updateTick(this.world, pos, blockState, this.rand);
            }
        }
        if(block.hasTileEntity(blockState)) {
            TileEntity tile = this.world.getTileEntity(pos);

            if(tile == null || tile.isInvalid()) return;

            for(int i = 0; i < this.speed; i++)
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
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.speed = nbt.getByte("Speed");
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
