package burn447.dartcraftReloaded.capablilities.BaneModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_BANE;

/**
 * Created by BURN447 on 6/16/2018.
 */
public class BaneProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;

    private IBaneModifier instance = null;


    public BaneProvider(Capability<IBaneModifier> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_BANE = capability;
            this.facing = facing;
            this.instance = CAPABILITY_BANE.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_BANE)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_BANE ? CAPABILITY_BANE.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {

    }

    public Capability<IBaneModifier> getCapability(){
        return CAPABILITY_BANE;
    }
}
