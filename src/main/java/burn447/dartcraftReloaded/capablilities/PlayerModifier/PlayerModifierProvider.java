package burn447.dartcraftReloaded.capablilities.PlayerModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_PLAYERMOD;

/**
 * Created by BURN447 on 6/21/2018.
 */
public class PlayerModifierProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;
    private IPlayerModifier instacne = null;

    public PlayerModifierProvider(Capability<IPlayerModifier> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_PLAYERMOD = capability;
            this.facing = facing;
            this.instacne = CAPABILITY_PLAYERMOD.getDefaultInstance();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_PLAYERMOD)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_PLAYERMOD ? CAPABILITY_PLAYERMOD.<T> cast(instacne) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_PLAYERMOD.getStorage().writeNBT(CAPABILITY_PLAYERMOD, instacne, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_PLAYERMOD.getStorage().readNBT(CAPABILITY_PLAYERMOD, instacne, null, nbt);
    }

    public final Capability<IPlayerModifier> getCapability(){
        return CAPABILITY_PLAYERMOD;
    }
}
