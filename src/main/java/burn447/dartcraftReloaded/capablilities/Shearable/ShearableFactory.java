package burn447.dartcraftReloaded.capablilities.Shearable;

import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.Callable;

/**
 * Created by BURN447 on 7/6/2018.
 */
public class ShearableFactory implements Callable<IShearableMob> {
    @Override
    public IShearableMob call() throws Exception {
        return new IShearableMob() {

            private boolean shearble = false;
            private boolean wasSheared = false;

            private final int time = 200;

            private int counter = time;


            @Override
            public boolean canBeSheared() {
                return shearble;
            }

            @Override
            public void update() {
                if(counter == time){
                    shearble = true;
                    if(wasSheared){
                        wasSheared = false;
                        counter = 0;
                    }
                }
                else if(counter < time){
                    shearble = false;
                    counter++;
                }
                else
                    counter = time;

            }

            @Override
            public int getTimer() {
                return counter;
            }

            @Override
            public void setTimer(int newTimer) {
                counter = newTimer;
            }

            @Override
            public void setShearable(boolean newVal) {
                shearble = newVal;
            }

            @Override
            public void setSheared(boolean wasSheared) {
                this.wasSheared = wasSheared;
            }

            @Override
            public NBTTagCompound serializeNBT() {
                return null;
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt) {

            }

        };
    }
}
