package burn447.dartcraftReloaded.capablilities.Magnet;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class MagnetStorage implements Capability.IStorage<IMagnet> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IMagnet> capability, IMagnet instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setBoolean("activated", instance.isActivated());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IMagnet> capability, IMagnet instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;
            instance.setActivation(nbt.getBoolean("activated"));
        }
    }
}
