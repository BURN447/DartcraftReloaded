package burn447.dartcraftReloaded.capablilities.ForceWrench;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BURN447 on 7/17/2018.
 */
public interface IForceWrench {

    boolean hasBlockStored();
    boolean canStoreBlock();
    NBTTagCompound getStoredBlockNBT();
    IBlockState getStoredBlockState();
    String getStoredName();

    void storeBlockNBT(NBTTagCompound nbt);
    void storeBlockState(IBlockState base);
    void setBlockName(String name);

    void clearBlockStorage();
}
