package burn447.dartcraftReloaded.util.capablilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ToolModStorage implements Capability.IStorage<IToolModifier> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbt) {

    }
}
