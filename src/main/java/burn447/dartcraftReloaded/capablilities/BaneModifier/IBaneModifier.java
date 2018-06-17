package burn447.dartcraftReloaded.capablilities.BaneModifier;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 6/16/2018.
 */
public interface IBaneModifier extends INBTSerializable<NBTTagCompound> {

    boolean canTeleport();
    void setTeleportAbility(boolean canTeleport);
}
