package burn447.dartcraftReloaded.util.capablilities;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;

public class ToolModStorage implements Capability.IStorage<IToolModifier> {


    public ToolModStorage(){
        System.out.println("Tool Mod Storage Initialization");
    }

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
            //System.out.println("Saving Efficiency");
            nbt.setFloat("efficiency", instance.getEfficiency());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = (NBTTagCompound) nbtIn;

            //System.out.println("Reading Efficiency " + nbt.getFloat("efficiency"));
            instance.setEfficiency(nbt.getFloat("efficiency"));
        }
    }


}
