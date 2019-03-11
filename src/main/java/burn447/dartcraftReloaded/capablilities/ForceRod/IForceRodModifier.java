package burn447.dartcraftReloaded.capablilities.ForceRod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 6/7/2018.
 */
public interface IForceRodModifier extends INBTSerializable<NBTTagCompound> {

    boolean isRodOfHealing(int level);
    void setRodOfHealing(boolean newVal, int level);

    boolean hasCamoModifier();
    void setCamoModifier(boolean newVal);

    BlockPos getHomeLocation();
    void setHomeLocation(BlockPos pos);
    void teleportPlayerToLocation(EntityPlayer player, BlockPos pos);
    boolean hasEnderModifier();
    void setEnderModifier(boolean newVal);
    boolean isRodofEnder();

    boolean hasSightModifier();
    void setSightModifier(boolean newVal);

    boolean hasLight();
    void setLight(boolean val);
}
