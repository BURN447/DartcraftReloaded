package burn447.dartcraftReloaded.capablilities.ForceRod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import java.util.concurrent.Callable;

/**
 * Created by BURN447 on 6/7/2018.
 */
public class ForceRodFactory implements Callable<IForceRodModifier> {
    @Override
    public IForceRodModifier call() throws Exception {
        return new IForceRodModifier() {

            boolean[] rodOfHealing = {false, false, false};
            boolean camo = false;
            boolean ender = false;
            boolean sight = false;

            BlockPos homeLocation = null;

            @Override
            public boolean isRodOfHealing(int level) {
                return rodOfHealing[level - 1];
            }

            @Override
            public void setRodOfHealing(boolean newVal, int level) {
                rodOfHealing[level - 1] = newVal;
            }

            @Override
            public boolean hasCamoModifier() {
                return camo;
            }

            @Override
            public void setCamoModifier(boolean newVal) {
                camo = newVal;
            }

            @Override
            public BlockPos getHomeLocation() {
                return homeLocation;
            }

            @Override
            public void setHomeLocation(BlockPos pos) {
                homeLocation = pos;
            }

            @Override
            public void teleportPlayerToLocation(EntityPlayer player, BlockPos pos) {
                int x = pos.getX();
                int y = pos.getY() + 1;
                int z = pos.getZ();

                player.attemptTeleport(x, y, z);
            }

            @Override
            public boolean hasEnderModifier() {
                return ender;
            }

            @Override
            public void setEnderModifier(boolean newVal) {
                ender = newVal;
            }

            @Override
            public boolean hasSightModifier() {
                return sight;
            }

            @Override
            public void setSightModifier(boolean newVal) {
                sight = newVal;
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
