package burn447.dartcraftReloaded.proxy;

import burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler;
import burn447.dartcraftReloaded.tileEntity.TileEntityForceFurnace;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
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
        GameRegistry.registerTileEntity(TileEntityForceFurnace.class, References.modId + ":blockFurnace");
    }

    @Mod.EventHandler
    public void preInit(){
        DCRCapabilityHandler.register();
    }
}