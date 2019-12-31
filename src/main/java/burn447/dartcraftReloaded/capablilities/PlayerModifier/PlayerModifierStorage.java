package burn447.dartcraftReloaded.capablilities.PlayerModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by BURN447 on 6/21/2018.
 */
public class PlayerModifierStorage implements Capability.IStorage<IPlayerModifier> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IPlayerModifier> capability, IPlayerModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setFloat("damage", instance.getDamage());
        nbt.setFloat("wingPower", instance.getWingPower());
        nbt.setFloat("flightCounter", instance.getFlightTimer());
        nbt.setFloat("heatDamage", instance.getHeatDamage());
        nbt.setFloat("attackDamage", instance.getAttackDamage());
        nbt.setInteger("luckLevel", instance.getLuckLevel());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerModifier> capability, IPlayerModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = ((NBTTagCompound) nbtIn);

            instance.setAttackDamage(nbt.getFloat("attackDamage"));
            instance.setWingPower(nbt.getFloat("wingPower"));
            instance.setFlightTimer(nbt.getFloat("flightCounter"));
            instance.setDamage(nbt.getFloat("damage"));
            instance.setLuckLevel(nbt.getInteger("luckLevel"));
        }
    }
}
