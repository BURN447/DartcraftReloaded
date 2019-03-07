package burn447.dartcraftReloaded.capablilities.ToolModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ToolModStorage implements Capability.IStorage<IToolModifier> {


    public ToolModStorage(){
    }

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        //Speed
        nbt.setInteger("speed", instance.getSpeedLevel());
        //Heat
        nbt.setBoolean("heat", instance.hasHeat());
        //Force
        nbt.setInteger("force", instance.getForceLevel());
        //Silk
        nbt.setBoolean("silk", instance.hasSilk());
        //Sharpness
        nbt.setInteger("sharp", instance.getSpeedLevel());
        //Luck
        nbt.setInteger("luck", instance.getLuckLevel());
        //Sturdy
        nbt.setInteger("sturdy", instance.getSturdyLevel());
        //Rainbow
        nbt.setBoolean("rainbow", instance.hasRainbow());
        //Lumberjack
        nbt.setBoolean("lumber", instance.hasLumberjack());
        //Bleeding
        nbt.setInteger("bleed", instance.getBleedLevel());
        //Bane
        nbt.setInteger("bane", instance.getBaneLevel());
        //Wing
        nbt.setBoolean("wing", instance.hasWing());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;
            instance.setSpeed(nbt.getInteger("speed"));
            instance.setHeat(nbt.getBoolean("heat"));
            instance.setForce(nbt.getInteger("force"));
            instance.setSpeed(nbt.getInteger("silk"));
            instance.setSharp(nbt.getInteger("sharp"));
            instance.setLuck(nbt.getInteger("luck"));
            instance.setSturdy(nbt.getInteger("sturdy"));
            instance.setRainbow(nbt.getBoolean("rainbow"));
            instance.setLumberjack(nbt.getBoolean("lumber"));
            instance.setBleed(nbt.getInteger("bleed"));
            instance.setBane(nbt.getInteger("bane"));
            instance.setWing(nbt.getBoolean("wing"));
        }
    }


}
