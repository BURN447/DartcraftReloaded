package burn447.dartcraftReloaded.capablilities.ForceRod;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_FORCEROD;

/**
 * Created by BURN447 on 6/7/2018.
 */
public class ForceRodProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;
    private IForceRodModifier instacne = null;

    public ForceRodProvider(Capability<IForceRodModifier> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_FORCEROD = capability;
            this.facing = facing;
            this.instacne = CAPABILITY_FORCEROD.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_FORCEROD)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_FORCEROD ? CAPABILITY_FORCEROD.<T> cast(instacne) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_FORCEROD.getStorage().writeNBT(CAPABILITY_FORCEROD, instacne, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_FORCEROD.getStorage().readNBT(CAPABILITY_FORCEROD, instacne, null, nbt);
    }

    public final Capability<IForceRodModifier> getCapability(){
        return CAPABILITY_FORCEROD;
    }
}
