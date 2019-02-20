package burn447.dartcraftReloaded.capablilities.ToolModifier;


import burn447.dartcraftReloaded.util.References;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Set;

/**
 * Created by BURN447 on 5/15/2018.
 */
public interface IToolModifier {
    /**
     * Modifier: Speed
     * Item: Sugar
     * Levels: 5
     * Effect: Gives Player Haste [Level] when holding the tool
     */
    int getSpeedLevel();
    boolean hasSpeed();
    void incrementSpeed();
    void setSpeed(int newSpeed);
}
