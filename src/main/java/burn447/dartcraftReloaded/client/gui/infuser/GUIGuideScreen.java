package burn447.dartcraftReloaded.client.gui.infuser;

import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIGuideScreen extends GuiScreen {

    private ResourceLocation TEXTURE = new ResourceLocation(References.modId, "textures/gui/guide/guide.png");

    private int xSize;
    private int ySize;

    private int guiLeft;
    private int guiTop;

    public GUIGuideScreen() {
        this.xSize = 204;
        this.ySize = 176;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(TEXTURE);
        //(ScreenX, ScreenY,
        drawTexturedModalRect(guiLeft, guiTop, 100, 100, 100, 100);

    }
}
