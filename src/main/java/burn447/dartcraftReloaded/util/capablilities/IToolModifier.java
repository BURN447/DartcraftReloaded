package burn447.dartcraftReloaded.util.capablilities;


import burn447.dartcraftReloaded.util.References;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 5/15/2018.
 */
public interface IToolModifier extends INBTSerializable<NBTTagCompound> {

    float efficiency = 16.0F;

    default boolean canApplyModifier(ItemStack stack, References.MODIFIERS mod){
        return false;
    }

    ItemStack applySpeedModifier(ItemStack stack, int level);
}
