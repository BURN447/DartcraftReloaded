package burn447.dartcraftReloaded.capablilities.Shearable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 7/6/2018.
 */
public interface IShearableMob extends INBTSerializable<NBTTagCompound> {

    boolean canBeSheared();
    void update();
    int getTimer();
    void setTimer(int newTimer);
    void setShearable(boolean newVal);
    void setSheared(boolean wasSheared);
}
