package burn447.dartcraftReloaded.capablilities.ExperienceTome;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_EXPTOME;

/**
 * Created by BURN447 on 6/10/2018.
 */
public class ExperienceTomeProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;
    private IExperienceTome instacne = null;

    public ExperienceTomeProvider(Capability<IExperienceTome> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_EXPTOME = capability;
            this.facing = facing;
            this.instacne = CAPABILITY_EXPTOME.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_EXPTOME)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_EXPTOME ? CAPABILITY_EXPTOME.<T> cast(instacne) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_EXPTOME.getStorage().writeNBT(CAPABILITY_EXPTOME, instacne, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_EXPTOME.getStorage().readNBT(CAPABILITY_EXPTOME, instacne, null, nbt);
    }

    public final Capability<IExperienceTome> getCapability(){
        return CAPABILITY_EXPTOME;
    }
}
