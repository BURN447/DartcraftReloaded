package burn447.dartcraftReloaded.capablilities.Magnet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMagnet extends INBTSerializable<NBTTagCompound> {

    boolean isActivated();

    void activate();
    void deactivate();

    void setActivation(boolean active);
}
