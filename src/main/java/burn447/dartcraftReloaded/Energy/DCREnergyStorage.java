package burn447.dartcraftReloaded.Energy;

import net.minecraftforge.energy.EnergyStorage;

/**
 * Created by BURN447 on 5/7/2018.
 */
public class DCREnergyStorage extends EnergyStorage {


    public DCREnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower(int energy) {
        this.energy -= energy;

        if(this.energy < 0) {
            this.energy = 0;
        }
    }
}