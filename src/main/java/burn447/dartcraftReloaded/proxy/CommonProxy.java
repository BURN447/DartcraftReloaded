package burn447.dartcraftReloaded.proxy;

import burn447.dartcraftReloaded.Handlers.CapabilityHandler;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import burn447.dartcraftReloaded.util.capablilities.ToolFactory;
import burn447.dartcraftReloaded.util.capablilities.ToolModStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, References.modId + ":blockInfuser");
    }

    @Mod.EventHandler
    public void init(){
        CapabilityManager.INSTANCE.register(IToolModifier.class, new ToolModStorage(), new ToolFactory());

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
}