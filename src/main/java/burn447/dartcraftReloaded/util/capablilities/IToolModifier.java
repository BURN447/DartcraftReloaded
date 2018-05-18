package burn447.dartcraftReloaded.util.capablilities;

import burn447.dartcraftReloaded.util.References;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by BURN447 on 5/15/2018.
 */
public interface IToolModifier extends INBTSerializable<NBTTagCompound> {

    /**
     *
     * @param stack
     *      Used to deterimine inserted stack
     * @param mod
     *      What modifier to apply
     * @return
     *      True or false on if it can be returned
     */
    boolean canApplyModifer(ItemStack stack, References.MODIFIERS mod);

    /**
     *
     * @param stack
     *      Stack in
     * @param mod
     *      Modifer in
     * @return
     *      Modified Item
     */
    ItemStack applyModifer(ItemStack stack, References.MODIFIERS mod);

}
