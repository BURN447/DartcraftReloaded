package burn447.dartcraftReloaded.proxy;

import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.client.gui.furnace.GUIFurnace;
import burn447.dartcraftReloaded.client.gui.infuser.GUIGuideScreen;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(References.modId + ":" + id, "inventory"));
    }

    @Override
    public String localize(String unlocalized, Object... args) {
        return I18n.format(unlocalized, args);
    }

    @Override
    public void openGuideGUI()
    {
        Minecraft.getMinecraft().displayGuiScreen(new GUIGuideScreen());
    }
}
