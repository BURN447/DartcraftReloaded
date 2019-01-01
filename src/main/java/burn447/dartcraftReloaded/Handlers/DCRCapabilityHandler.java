package burn447.dartcraftReloaded.Handlers;

import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneFactory;
import burn447.dartcraftReloaded.capablilities.BaneModifier.BaneModifierStorage;
import burn447.dartcraftReloaded.capablilities.BaneModifier.IBaneModifier;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeFactory;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.ExperienceTomeStorage;
import burn447.dartcraftReloaded.capablilities.ExperienceTome.IExperienceTome;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodFactory;
import burn447.dartcraftReloaded.capablilities.ForceRod.ForceRodStorage;
import burn447.dartcraftReloaded.capablilities.ForceRod.IForceRodModifier;
import burn447.dartcraftReloaded.capablilities.ForceWrench.ForceWrenchFactory;
import burn447.dartcraftReloaded.capablilities.ForceWrench.ForceWrenchStorage;
import burn447.dartcraftReloaded.capablilities.ForceWrench.IForceWrench;
import burn447.dartcraftReloaded.capablilities.PlayerModifier.IPlayerModifier;
import burn447.dartcraftReloaded.capablilities.PlayerModifier.PlayerModifierFactory;
import burn447.dartcraftReloaded.capablilities.PlayerModifier.PlayerModifierStorage;
import burn447.dartcraftReloaded.capablilities.Shearable.IShearableMob;
import burn447.dartcraftReloaded.capablilities.Shearable.ShearableFactory;
import burn447.dartcraftReloaded.capablilities.Shearable.ShearableStorage;
import burn447.dartcraftReloaded.capablilities.ToolModifier.IToolModifier;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolFactory;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModStorage;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DCRCapabilityHandler {

    public static final ResourceLocation BANE_CAP = new ResourceLocation(References.modId, "baneMod");
    public static final ResourceLocation PLAYER_CAP = new ResourceLocation(References.modId, "playerMod");
    public static final ResourceLocation SHEAR_CAP = new ResourceLocation(References.modId, "shearable");

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

    @CapabilityInject(IShearableMob.class)
    public static Capability<IShearableMob> CAPABILITY_SHEARABLE = null;

    @CapabilityInject(IForceWrench.class)
    public static Capability<IForceWrench> CAPABILITY_FORCEWRENCH = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new ToolModStorage(), new ToolFactory());
        CapabilityManager.INSTANCE.register(IForceRodModifier.class, new ForceRodStorage(), new ForceRodFactory());
        CapabilityManager.INSTANCE.register(IExperienceTome.class, new ExperienceTomeStorage(), new ExperienceTomeFactory());
        CapabilityManager.INSTANCE.register(IBaneModifier.class, new BaneModifierStorage(), new BaneFactory());
        CapabilityManager.INSTANCE.register(IShearableMob.class, new ShearableStorage(), new ShearableFactory());
        CapabilityManager.INSTANCE.register(IForceWrench.class, new ForceWrenchStorage(), new ForceWrenchFactory());
        CapabilityManager.INSTANCE.register(IPlayerModifier.class, new PlayerModifierStorage(), new PlayerModifierFactory());

        MinecraftForge.EVENT_BUS.register(new DCRCapabilityHandler());
    }
}
