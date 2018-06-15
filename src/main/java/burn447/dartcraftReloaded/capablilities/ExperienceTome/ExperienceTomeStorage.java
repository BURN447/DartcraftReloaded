package burn447.dartcraftReloaded.capablilities.ExperienceTome;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/10/2018.
 */
public class ExperienceTomeStorage implements Capability.IStorage<IExperienceTome> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IExperienceTome> capability, IExperienceTome instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("experience", instance.getExperienceValue());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IExperienceTome> capability, IExperienceTome instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound) {
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;
            instance.setExperienceValue(instance.getExperienceValue());

        }
    }
}
