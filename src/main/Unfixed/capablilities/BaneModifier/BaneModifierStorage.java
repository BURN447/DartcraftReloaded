package burn447.dartcraftReloaded.capablilities.BaneModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class BaneModifierStorage implements Capability.IStorage<IBaneModifier> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBaneModifier> capability, IBaneModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        
        nbt.setBoolean("canTeleport", instance.canTeleport());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IBaneModifier> capability, IBaneModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = ((NBTTagCompound) nbtIn);

            instance.setTeleportAbility(nbt.getBoolean("canTeleport"));
        }
    }
}
