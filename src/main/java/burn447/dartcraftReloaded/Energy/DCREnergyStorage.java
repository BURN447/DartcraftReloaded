package burn447.dartcraftReloaded.Energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

/**
 * Created by BURN447 on 5/7/2018.
 */
public class DCREnergyStorage extends EnergyStorage {

    public DCREnergyStorage(int capacity){
        super(capacity, capacity, capacity, 0);
    }

    public DCREnergyStorage(int capacity, int maxTransfer){
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public DCREnergyStorage(int capacity, int maxRecieve, int maxExtract){
        super(capacity, maxRecieve, maxExtract, 0);
    }

    public DCREnergyStorage(int capacity, int maxRecieve, int maxExtract, int energy){
        super(capacity, maxRecieve, maxExtract, energy);
    }

    public int extractEnergyInternal(int maxExtract, boolean simulate){
        int before = this.maxExtract;
        this.maxExtract = Integer.MAX_VALUE;

        int toReturn = this.extractEnergy(maxExtract, simulate);

        this.maxExtract = before;
        return toReturn;
    }

    public int recieveEnergyInternal(int maxRecieve, boolean simulate){
        int before = maxRecieve;
        this.maxReceive = Integer.MAX_VALUE;

        int toReturn = receiveEnergy(maxRecieve, simulate);

        this.maxReceive = before;
        return toReturn;
    }

    public void readFromNBT(NBTTagCompound nbt){
        nbt.getInteger("Energy");
        nbt.getInteger("Capacity");
        nbt.getInteger("MaxRecieve");
        nbt.getInteger("MaxExtract");
    }

    public void writeToNBT(NBTTagCompound nbt){
        nbt.setInteger("Energy", energy);
        nbt.setInteger("Capacity", capacity);
        nbt.setInteger("MaxRecieve", maxReceive);
        nbt.setInteger("MaxExtract", maxExtract);
    }

    public DCREnergyStorage setCapacity(int capacity){
        this.capacity = capacity;
        return this;
    }

    public DCREnergyStorage setMaxTransfer(int transfer){
        this.maxExtract = transfer;
        this.maxReceive = transfer;
        return this;
    }

    public DCREnergyStorage setMaxRecieve(int maxRecieve){
        this.maxReceive = maxRecieve;
        return this;
    }

    public DCREnergyStorage setMaxExtract(int maxExtract){
        this.maxExtract = maxExtract;
        return this;
    }

    public int getMaxTransfer(){
        return this.maxReceive == this.maxExtract ? this.maxReceive : Math.max(this.maxReceive, this.maxExtract);
    }

    public int getMaxRecieve(){
        return this.maxReceive;
    }

    public int getMaxExtract(){
        return this.maxExtract;
    }

}

