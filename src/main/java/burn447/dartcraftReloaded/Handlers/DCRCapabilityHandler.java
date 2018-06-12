package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeFactory;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeStorage;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.IExperienceTome;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodFactory;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodStorage;
import burn447.dartcraftReloaded.capablilities.ForceRod.IForceRodModifier;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.capablilities.ToolModifier.IToolModifier;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolFactory;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModStorage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DCRCapabilityHandler {

    public static final ResourceLocation TOOLMOD_CAP = new ResourceLocation(References.modId, "toolMod");

    @CapabilityInject(IToolModifier.class)
    public static Capability<IToolModifier> CAPABILITY_TOOLMOD = null;

    @CapabilityInject(IForceRodModifier.class)
    public static Capability<IForceRodModifier> CAPABILITY_FORCEROD = null;

    @CapabilityInject(IExperienceTome.class)
    public static Capability<IExperienceTome> CAPABILITY_EXPTOME = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new ToolModStorage(), new ToolFactory());
        CapabilityManager.INSTANCE.register(IForceRodModifier.class, new ForceRodStorage(), new ForceRodFactory());
        CapabilityManager.INSTANCE.register(IExperienceTome.class, new ExperienceTomeStorage(), new ExperienceTomeFactory());

        MinecraftForge.EVENT_BUS.register(new DCRCapabilityHandler());
    }
}
