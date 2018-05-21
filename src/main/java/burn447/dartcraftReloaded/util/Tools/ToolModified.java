package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.proxy.CommonProxy;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by BURN447 on 5/20/2018.
 */
public class ToolModified implements IToolModifier, Capability.IStorage<IToolModifier> , ICapabilityProvider{

    public float efficiency = 8.0F;

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

        NBTTagCompound nbt = stack.getTagCompound();

        System.out.println("APPLY SPEED CHANGE");
        efficiency = 24.0F;

        nbt.setFloat("speed", efficiency);

        stack.setTagCompound(nbt);

        return stack;
    }

    @Nullable
    @Override
    public NBTTagCompound writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        deserializeNBT(nbt);
        return null;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbt) {

    }


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CommonProxy.CAPABILITY_TOOLMOD){
            return true;
        }
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return null;
    }
}
