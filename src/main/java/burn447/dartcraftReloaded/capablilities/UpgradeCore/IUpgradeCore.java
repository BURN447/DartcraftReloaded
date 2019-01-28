package burn447.dartcraftReloaded.capablilities.UpgradeCore;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IUpgradeCore extends INBTSerializable<NBTTagCompound> {

    //Luck Modifier
    boolean hasLuckLevel(int level);
    boolean hasAnyLuck();
    void setLuckOne(boolean newVal);
    void setLuckTwo(boolean newVal);
    void setLuckThree(boolean newVal);
    void setLuckFour(boolean newVal);
    void setLuck(int level);
    int luckLevel();

    boolean hasSpeed();
    void setSpeed(boolean newVal);
}
