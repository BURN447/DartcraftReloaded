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

    /**
     * Modifier Heat
     * Item: Golden Power Source
     * Levels: 1
     * Effect: Auto-Smelt Item drops
     */
    boolean hasHeat();
    void setHeat(boolean val);

    /**
     * Modifier: Force
     * Item: Force Nugget
     * Levels: 3
     * Effect: Gives the Sword Knockback
     */
    int getForceLevel();
    boolean hasForce();
    void incrementForce();
    void setForce(int newForce);

    /**
     * Modifier Silk
     * Item: Web
     * Levels: 1
     * Effect: Give Pick/Shovel/Axe Silk Touch
     */
    boolean hasSilk();
    void setSilk(boolean val);

    /**
     * Modifier: Sharpness
     * Item: Claw
     * Levels: 10
     * Effect: Adds Sharpness to Force Sword
     */
    int getSharpLevel();
    boolean hasSharp();
    void incrementSharp();
    void setSharp(int newSharp);

    /**
     * Modifier: Luck
     * Item: Fortune
     * Levels: 5
     * Effect: Adds Fortune to a tool or Looting to a sword
     */
    int getLuckLevel();
    boolean hasLuck();
    void incrementLuck();
    void setLuck(int newLuck);
}
