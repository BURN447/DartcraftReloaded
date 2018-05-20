package burn447.dartcraftReloaded.proxy;

import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.Tools.ToolModified;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import javax.tools.Tool;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }

    public void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityInfuser.class, References.modId + ":blockInfuser");
    }

    @CapabilityInject(IToolModifier.class)
    public static final Capability<IToolModifier> CAPABILITY_TOOLMOD = null;

    public static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new CapabilityToolMod(), ToolModified.class);
    }

    public static class CapabilityToolMod implements Capability.IStorage<IToolModifier>{

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IToolModifier> capability, IToolModifier instance, EnumFacing side, NBTBase nbt) {

        }
    }
}
