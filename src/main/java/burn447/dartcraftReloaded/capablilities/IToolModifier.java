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

    float getAttackDamage();

    float getAttackSpeed();

    Item.ToolMaterial getToolMaterial();

    float getEfficiency();

    void setEfficiency(float newEfficiency);

}
