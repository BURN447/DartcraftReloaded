package burn447.dartcraftReloaded.client.gui.infuser;

import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        //this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
    }
}
