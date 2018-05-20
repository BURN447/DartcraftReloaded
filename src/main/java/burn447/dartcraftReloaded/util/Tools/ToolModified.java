package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BURN447 on 5/20/2018.
 */
public class ToolModified implements IToolModifier {

    public float efficiency;

    public ToolModified(){

    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    @Override
    public boolean canApplyModifier(ItemStack stack, References.MODIFIERS mod) {
        return false;
    }

    @Override
    public ItemStack applySpeedModifier(ItemStack stack, int level) {
        efficiency = 24.0F;

        return stack;
    }
}
