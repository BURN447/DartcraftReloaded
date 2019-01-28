package burn447.dartcraftReloaded.capablilities.UpgradeCore;

import net.minecraft.nbt.NBTTagCompound;

import java.util.concurrent.Callable;

public class UpgradeCoreFactory implements Callable<IUpgradeCore> {
    @Override
    public IUpgradeCore call() throws Exception {
        return new IUpgradeCore() {

            Boolean luckLevels[] = {false, false, false, false};
            Boolean speed = false;

            @Override
            public boolean hasLuckLevel(int level) {
                return luckLevels[level];
            }

            @Override
            public boolean hasAnyLuck() {
                return luckLevels[0] || luckLevels[1] || luckLevels[2] || luckLevels[3];
            }

            @Override
            public void setLuckOne(boolean newVal) {
                luckLevels[0] = newVal;
            }

            @Override
            public void setLuckTwo(boolean newVal) {
                luckLevels[1] = newVal;
            }

            @Override
            public void setLuckThree(boolean newVal) {
                luckLevels[2] = newVal;
            }

            @Override
            public void setLuckFour(boolean newVal) {
                luckLevels[3] = newVal;
            }

            @Override
            public void setLuck(int level) {
                luckLevels[level] = true;
            }

            @Override
            public int luckLevel() {
                if(luckLevels[3])
                    return 4;
                else if (luckLevels[2])
                    return 3;
                else if (luckLevels[1])
                    return 2;
                else if (luckLevels[0])
                    return 1;
                else return 0;
            }

            @Override
            public boolean hasSpeed() {
                return speed;
            }

            @Override
            public void setSpeed(boolean newVal) {
                speed = newVal;
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
