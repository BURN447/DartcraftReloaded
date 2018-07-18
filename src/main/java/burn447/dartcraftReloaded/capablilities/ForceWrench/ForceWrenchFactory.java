package burn447.dartcraftReloaded.capablilities.ForceWrench;

import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.Callable;

/**
 * Created by BURN447 on 7/17/2018.
 */
public class ForceWrenchFactory implements Callable<IForceWrench> {
    @Override
    public IForceWrench call() throws Exception {
        return new IForceWrench() {

            NBTTagCompound storedBlockNBT = null;
            IBlockState storedBlockState = null;


            @Override
            public boolean hasBlockStored() {
                if(storedBlockState == null)
                    return false;
                else
                    return true;
            }

            @Override
            public boolean canStoreBlock() {
                return hasBlockStored();
            }

            @Override
            public NBTTagCompound getStoredBlockNBT() {
                return storedBlockNBT;
            }

            @Override
            public IBlockState getStoredBlockState() {
                return storedBlockState;
            }

            @Override
            public void storeBlockNBT(NBTTagCompound nbt) {
                storedBlockNBT = nbt;
            }

            @Override
            public void storeBlockState(IBlockState base) {
                storedBlockState = base;
            }

            @Override
            public void clearBlockStorage() {
                storedBlockState = null;
                storedBlockNBT = null;
            }
        };
    }
}
