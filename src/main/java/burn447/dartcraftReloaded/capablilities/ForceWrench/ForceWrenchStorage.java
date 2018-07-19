package burn447.dartcraftReloaded.capablilities.ForceWrench;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 7/17/2018.
 */
public class ForceWrenchStorage implements Capability.IStorage<IForceWrench> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IForceWrench> capability, IForceWrench instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        if(instance.getStoredBlockNBT() != null)
            nbt.setTag("storedNBT", instance.getStoredBlockNBT());
        if(instance.getStoredName() != null)
            nbt.setString("name", instance.getStoredName());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IForceWrench> capability, IForceWrench instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = ((NBTTagCompound) nbtIn);

            instance.storeBlockNBT(nbt.getCompoundTag("storedNBT"));
            instance.setBlockName(nbt.getString("name"));
        }
    }
}
