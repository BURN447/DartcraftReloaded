package burn447.dartcraftReloaded.capablilities;


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
public interface IToolModifier extends INBTSerializable<NBTTagCompound> {

    float getDestroySpeed(ItemStack stack, IBlockState state);

    int getItemEnchantibility();

    default boolean canApplyModifier(ItemStack stack, References.MODIFIERS mod){
        return false;
    }

    Set<Block> getEffectiveBlocks();

    Item.ToolMaterial getToolMaterial();

    //Speed Modifer
    float getEfficiency();

    void setEfficiency(float newEfficiency);

    //Damage Modifier
    float getAttackDamage();
    void setAttackDamage(float newVal);

    //Freezing Modifier
    boolean hasFreezing();
    void setFreezing(boolean newVal);

    //Heat Modifier
    boolean hasHeat();
    void setHeat(boolean newVal);

    //Luck Modifier
    boolean hasLuckOne();
    void setLuckOne(boolean newVal);
    boolean hasLuckTwo();
    void setLuckTwo(boolean newVal);
    boolean hasLuckThree();
    void setLuckThree(boolean newVal);
    boolean hasLuckFour();
    void setLuckFour(boolean newVal);
    void setLuck(int level);

    //Wing Modifier
    boolean hasWing();
    void setWing(boolean newVal);

    //Bane
    boolean hasBane();
    void setBane(boolean newVal);

    //Bleed
    boolean hasBleeding();
    void setBleeding(boolean newVal);

    //Light
    boolean hasLightOne();
    void setLightOne(boolean newVal);
    boolean hasLightTwo();
    void setLightTwo(boolean newVal);
    boolean hasLightThree();
    void setLightThree(boolean newVal);
    boolean hasLightFour();
    void setLightFour(boolean newVal);
    boolean hasLightFive();
    void setLightFive(boolean newVal);
    void setLight(int level);

    //Repair
    boolean hasRepair();
    void setRepair(boolean newVal);

    //Soul
    boolean hasSoul();
    void setSoul(boolean newVal);

    //Treasure
    boolean hasTreasure();
    void setTreasure(boolean newVal);

    //Unbreakable
    boolean isUnbreakable();
    void setUnbreaking(boolean newVal);

    //Grinding
    boolean hasGrinding();
    void setGrinding(boolean newVal);

    //Silk touch
    boolean hasTouch();
    void setTouch(boolean newVal);

    //Sturdy
    boolean hasSturdy();
    void setSturdy(boolean newVal);

    //Grafting
    //TODO: Needs Foresty Integration First

    //Rainbow
    //TODO: Implement Shears

    //Force
    float getKnockback();
    void setKnockback(float newVal);

}
