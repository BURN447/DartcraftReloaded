package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import burn447.dartcraftReloaded.util.capablilities.ToolFactory;
import burn447.dartcraftReloaded.util.capablilities.ToolModStorage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DCRCapabilityHandler {

    public static final ResourceLocation TOOLMOD_CAP = new ResourceLocation(References.modId, "toolMod");

    @CapabilityInject(IToolModifier.class)
    public static Capability<IToolModifier> CAPABILITY_TOOLMOD = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new ToolModStorage(), new ToolFactory());

        MinecraftForge.EVENT_BUS.register(new DCRCapabilityHandler());
    }
}
