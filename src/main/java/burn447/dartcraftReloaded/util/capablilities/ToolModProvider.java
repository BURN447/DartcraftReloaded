package burn447.dartcraftReloaded.util.capablilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ToolModProvider implements ICapabilitySerializable<NBTBase>, ICapabilityProvider {

    @CapabilityInject(IToolModifier.class)
    private final Capability<IToolModifier> CAPABILITY_TOOLMOD;

    private final EnumFacing facing;

    private final IToolModifier instance;

//    public ToolModProvider(Capability<IToolModifier> capability, EnumFacing facing){
//        this(capability, facing, capability.getDefaultInstance());
//    }

    public ToolModProvider(Capability<IToolModifier> capability, EnumFacing facing, IToolModifier instance){
        this.CAPABILITY_TOOLMOD = capability;
        this.facing = facing;
        this.instance = instance;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == getCapability();
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
