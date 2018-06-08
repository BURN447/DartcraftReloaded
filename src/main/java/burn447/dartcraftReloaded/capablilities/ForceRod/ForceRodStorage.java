package burn447.dartcraftReloaded.capablilities.ForceRod;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/7/2018.
 */
public class ForceRodStorage implements Capability.IStorage<IForceRodModifier> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IForceRodModifier> capability, IForceRodModifier instance, EnumFacing side) {
        NBTTagCompound nbt  = new NBTTagCompound();

        nbt.setBoolean("healingOne", instance.isRodOfHealing(1));
        nbt.setBoolean("healingTwo", instance.isRodOfHealing(2));
        nbt.setBoolean("healingThree", instance.isRodOfHealing(3));

        nbt.setBoolean("camo", instance.hasCamoModifier());

        nbt.setBoolean("ender", instance.hasEnderModifier());

        nbt.setBoolean("sight", instance.hasSightModifier());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IForceRodModifier> capability, IForceRodModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;
            instance.setRodOfHealing(nbt.getBoolean("healingOne"), 1);
            instance.setRodOfHealing(nbt.getBoolean("healingTwo"), 2);
            instance.setRodOfHealing(nbt.getBoolean("healingThree"), 3);

            instance.setCamoModifier(nbt.getBoolean("camo"));

            instance.setEnderModifier(nbt.getBoolean("ender"));

            instance.setSightModifier(nbt.getBoolean("sight"));
        }
    }
}
