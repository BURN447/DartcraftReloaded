package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneFactory;
import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneModifierStorage;
import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneProvider;
import burn447.dartcraftReloaded.capablilities.BaneModifier.IBaneModifier;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeFactory;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeStorage;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.IExperienceTome;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodFactory;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodStorage;
import burn447.dartcraftReloaded.capablilities.ForceRod.IForceRodModifier;
import burn447.dartcraftReloaded.capablilities.PlayerModifier.IPlayerModifier;
import burn447.dartcraftReloaded.capablilities.ToolModifier.IToolModifier;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolFactory;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModStorage;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DCRCapabilityHandler {

    public static final ResourceLocation BANE_CAP = new ResourceLocation(References.modId, "baneMod");
    public static final ResourceLocation PLAYER_CAP = new ResourceLocation(References.modId, "playerMod");

    @CapabilityInject(IToolModifier.class)
    public static Capability<IToolModifier> CAPABILITY_TOOLMOD = null;

    @CapabilityInject(IForceRodModifier.class)
    public static Capability<IForceRodModifier> CAPABILITY_FORCEROD = null;

    @CapabilityInject(IExperienceTome.class)
    public static Capability<IExperienceTome> CAPABILITY_EXPTOME = null;

    @CapabilityInject(IBaneModifier.class)
    public static Capability<IBaneModifier> CAPABILITY_BANE = null;

    @CapabilityInject(IPlayerModifier.class)
    public static Capability<IPlayerModifier> CAPABILITY_PLAYERMOD = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new ToolModStorage(), new ToolFactory());
        CapabilityManager.INSTANCE.register(IForceRodModifier.class, new ForceRodStorage(), new ForceRodFactory());
        CapabilityManager.INSTANCE.register(IExperienceTome.class, new ExperienceTomeStorage(), new ExperienceTomeFactory());
        CapabilityManager.INSTANCE.register(IBaneModifier.class, new BaneModifierStorage(), new BaneFactory());

        MinecraftForge.EVENT_BUS.register(new DCRCapabilityHandler());
    }
}
