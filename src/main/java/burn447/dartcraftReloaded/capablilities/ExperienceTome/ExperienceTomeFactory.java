package burn447.dartcraftReloaded.capablilities.ExperienceTome;

import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.Callable;

/**
 * Created by BURN447 on 6/10/2018.
 */
public class ExperienceTomeFactory implements Callable<IExperienceTome> {
    @Override
    public IExperienceTome call() throws Exception {
        return new IExperienceTome() {

            private float experienceStored = 0.0F;

            @Override
            public float getExperienceValue() {
                return experienceStored;
            }

            @Override
            public void addToExperienceValue() {

            }

            @Override
            public void subtractFromExperienceValue() {

            }

            @Override
            public void setExperienceValue(float newExp) {
                experienceStored = newExp;
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
