package burn447.dartcraftReloaded.capablilities.Shearable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 7/6/2018.
 */
public class ShearableStorage implements Capability.IStorage<IShearableMob> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IShearableMob> capability, IShearableMob instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("timer", instance.getTimer());
        nbt.setBoolean("shearble", instance.canBeSheared());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IShearableMob> capability, IShearableMob instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;

            instance.setTimer(nbt.getInteger("timer"));
        }
    }
}
