package burn447.dartcraftReloaded.client.gui;

import burn447.dartcraftReloaded.Handlers.DCRPacketHandler;
import burn447.dartcraftReloaded.Networking.InfuserMessage;
import burn447.dartcraftReloaded.container.ContainerBlockInfuser;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class GUIInfuser extends GuiContainer {

    private TileEntityInfuser te;
    private GuiButton startButton = new GuiButton(0, 39, 101, 12, 12, "Start Button");
    private ProgressBar infuserProgress;

    private ResourceLocation TEXTURE = new ResourceLocation(References.modId, "textures/gui/container/forceinfuser.png");


    public GUIInfuser(IInventory playerInv, TileEntityInfuser te) {
        super(new ContainerBlockInfuser(playerInv, te));

        this.xSize = 176;
        this.ySize = 208;

        this.te = te;

        this.addButton(startButton);

        this.infuserProgress = new ProgressBar(TEXTURE, ProgressBar.ProgressBarDirection.DOWN_TO_UP, 2, 20, 134, 93, 176, 0);

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0,0,this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);

        this.infuserProgress.setMin(te.processTime).setMax(te.maxProcessTime);
        this.infuserProgress.draw(this.mc);

        if (isPointInRegion(123, 16, 12, 12, mouseX, mouseY) && te.handler.getStackInSlot(9).isEmpty()) {
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.GRAY + I18n.format("gui.blockInfuserHelp.tooltip"));
            this.drawHoveringText(text, actualMouseX, actualMouseY);
        }

        if(isPointInRegion(39, 101, 12, 12, mouseX, mouseY)){
            List<String> text = new ArrayList<>();
            text.add(TextFormatting.GRAY + I18n.format("gui.blockInfuser.Start.tooltip"));
            this.drawHoveringText(text, actualMouseX, actualMouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(isPointInRegion(39, 101, 12, 12, mouseX, mouseY)){
            this.actionPerformed(startButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        //Start Infusion
        if(button.id == 0){
            DCRPacketHandler.sendToServer(new InfuserMessage(true));
            te.canWork = true;
        }
    }

}