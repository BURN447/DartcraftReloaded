package burn447.dartcraftReloaded.util.capablilities;

import burn447.dartcraftReloaded.util.Tools.ToolModified;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

public class ToolModProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    private EnumFacing facing = null;

    private IToolModifier instance = null;

    private ToolModified toolModified = new ToolModified();

    public ToolModProvider(Capability<IToolModifier> capability, EnumFacing facing){
        if(capability != null){
            CAPABILITY_TOOLMOD = capability;
            this.facing = facing;
            this.instance = CAPABILITY_TOOLMOD.getDefaultInstance();
        }
    }


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_TOOLMOD)
            return capability == getCapability();
        else
            return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_TOOLMOD ? CAPABILITY_TOOLMOD.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CAPABILITY_TOOLMOD.getStorage().writeNBT(CAPABILITY_TOOLMOD, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CAPABILITY_TOOLMOD.getStorage().readNBT(CAPABILITY_TOOLMOD, instance, null, nbt);
    }

    public final Capability<IToolModifier> getCapability(){
        return CAPABILITY_TOOLMOD;
    }
}
