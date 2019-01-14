package burn447.dartcraftReloaded.capablilities.Magnet;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_MAGNET;

public class MagnetProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;

    private IMagnet instance = null;


    public MagnetProvider(Capability<IMagnet> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_MAGNET = capability;
            this.facing = facing;
            this.instance = CAPABILITY_MAGNET.getDefaultInstance();
        }
    }


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_MAGNET)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_MAGNET ? CAPABILITY_MAGNET.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_MAGNET.getStorage().writeNBT(CAPABILITY_MAGNET, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_MAGNET.getStorage().readNBT(CAPABILITY_MAGNET, instance, null, nbt);
    }

    public final Capability<IMagnet> getCapability(){
        return CAPABILITY_MAGNET;
    }
}
