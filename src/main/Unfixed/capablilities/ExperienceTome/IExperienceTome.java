package burn447.dartcraftReloaded.capablilities.ExperienceTome;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 6/10/2018.
 */
public interface IExperienceTome extends INBTSerializable<NBTTagCompound> {

    float getExperienceValue();
    void addToExperienceValue();
    void subtractFromExperienceValue();
    void setExperienceValue(float newExp);

}
