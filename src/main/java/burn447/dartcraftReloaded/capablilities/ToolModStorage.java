package burn447.dartcraftReloaded.capablilities;

import net.minecraft.nbt.NBTBase;
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
            nbt.setFloat("efficiency", instance.getEfficiency());
            nbt.setBoolean("heat", instance.hasHeat());
            nbt.setFloat("knockback", instance.getKnockback());
            nbt.setBoolean("grinding", instance.hasGrinding());
            nbt.setBoolean("silkTouch", instance.hasTouch());
            nbt.setFloat("damage", instance.getAttackDamage());

            //Luck
            nbt.setBoolean("luckOne", instance.hasLuckOne());       nbt.setBoolean("luckTwo", instance.hasLuckTwo());
            nbt.setBoolean("luckThree", instance.hasLuckThree());   nbt.setBoolean("luckFour", instance.hasLuckFour());

            //Light
        nbt.setBoolean("lightOne", instance.hasLightOne());         nbt.setBoolean("lightTwo", instance.hasLightTwo());
        nbt.setBoolean("lightThree", instance.hasLightThree());     nbt.setBoolean("lightFour", instance.hasLightFour());
        nbt.setBoolean("lightFive", instance.hasLightFive());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;
            instance.setEfficiency(nbt.getFloat("efficiency"));
            instance.setHeat(nbt.getBoolean("heat"));
            instance.setKnockback(nbt.getFloat("knockback"));
            instance.setGrinding(nbt.getBoolean("grinding"));
            instance.setTouch(nbt.getBoolean("silkTouch"));
            instance.setAttackDamage(nbt.getFloat("damage"));

            //Luck
            instance.setLuckOne(nbt.getBoolean("luckOne"));     instance.setLuckTwo(nbt.getBoolean("luckTwo"));
            instance.setLuckThree(nbt.getBoolean("luckThree")); instance.setLuckFour(nbt.getBoolean("luckFour"));

            //Light
            instance.setLightOne(nbt.getBoolean("lightOne"));       instance.setLightTwo(nbt.getBoolean("lightTwo"));
            instance.setLightThree(nbt.getBoolean("lightThree"));   instance.setLightFour(nbt.getBoolean("lightFour"));
            instance.setLightFive(nbt.getBoolean("lightFive"));


        }
    }


}
