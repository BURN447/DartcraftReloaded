package burn447.dartcraftReloaded.capablilities.ForceWrench;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_FORCEWRENCH;

/**
 * Created by BURN447 on 7/17/2018.
 */
public class ForceWrenchProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;
    private IForceWrench instance = null;

    public ForceWrenchProvider(Capability<IForceWrench> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_FORCEWRENCH = capability;
            this.facing = facing;
            this.instance = CAPABILITY_FORCEWRENCH.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_FORCEWRENCH)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_FORCEWRENCH ? CAPABILITY_FORCEWRENCH.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_FORCEWRENCH.getStorage().writeNBT(CAPABILITY_FORCEWRENCH, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_FORCEWRENCH.getStorage().readNBT(CAPABILITY_FORCEWRENCH, instance, null, nbt);
    }

    public final Capability<IForceWrench> getCapability(){
        return CAPABILITY_FORCEWRENCH;
    }
}
