package burn447.dartcraftReloaded.client.gui.guide;

import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.GuiAdvancementTab;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.CPacketSeenAdvancements;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.util.Map;

public class GUIGuideBook extends GuiScreenAdvancements {

    public static final ResourceLocation WINDOW = new ResourceLocation(References.modId,"textures/gui/guide/mudoraBg.png");
    private int scrollMouseX;
    private int scrollMouseY;
    private boolean isScrolling;

    public GUIGuideBook(ClientAdvancementManager p_i47383_1_) {
        super(p_i47383_1_);
    }

    public GUIGuideBook() {
        super(new ClientAdvancementManager(Minecraft.getMinecraft()));
    }

    @Override
    public void onGuiClosed() {
        NetHandlerPlayClient nethandlerplayclient = this.mc.getConnection();

        if (nethandlerplayclient != null)
        {
            nethandlerplayclient.sendPacket(CPacketSeenAdvancements.closedScreen());
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void renderWindow(int p_191934_1_, int p_191934_2_) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.disableStandardItemLighting();
        this.mc.getTextureManager().bindTexture(WINDOW);
        this.drawTexturedModalRect(p_191934_1_, p_191934_2_, 0, 0, 250, 200);

        this.fontRenderer.drawString(I18n.format("gui.guideBook"), p_191934_1_ + 8, p_191934_2_ + 6, 4210752);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i = (this.width - 250) / 2;
        int j = (this.height - 200) / 2;

        if (Mouse.isButtonDown(0))
        {
            if (!this.isScrolling)
            {
                this.isScrolling = true;
            }

            this.scrollMouseX = mouseX;
            this.scrollMouseY = mouseY;
        }
        else
        {
            this.isScrolling = false;
        }

        this.drawDefaultBackground();
        this.renderInside(mouseX, mouseY, i, j);
        this.renderWindow(i, j);
        //super.drawScreen(mouseX, mouseY, partialTicks);
        //this.renderToolTips(mouseX, mouseY, i, j);
    }

    private void renderInside(int p_191936_1_, int p_191936_2_, int p_191936_3_, int p_191936_4_)
    {
        drawRect(p_191936_3_ + 9, p_191936_4_ + 18, p_191936_3_ + 9 + 234, p_191936_4_ + 18 + 173, -16777216);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(p_191936_3_ + 9), (float)(p_191936_4_ + 18), -400.0F);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
        GlStateManager.depthFunc(515);
        GlStateManager.disableDepth();
    }
}
