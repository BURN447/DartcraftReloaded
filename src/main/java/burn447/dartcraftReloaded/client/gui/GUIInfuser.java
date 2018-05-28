package burn447.dartcraftReloaded.client.gui;

import burn447.dartcraftReloaded.container.ContainerBlockInfuser;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class GUIInfuser extends GuiContainer {

    private TileEntityInfuser te;
    private GuiButton startButton = new GuiButton(0, 39, 101, 12, 12, "Test Button");

    public GUIInfuser(IInventory playerInv, TileEntityInfuser te) {
        super(new ContainerBlockInfuser(playerInv, te));

        this.xSize = 176;
        this.ySize = 208;

        this.te = te;

        this.addButton(startButton);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.modId, "textures/gui/container/forceinfuser.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0,0,this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);

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

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);

        if(isPointInRegion(39, 101, 12, 12, mouseX, mouseY)){
            System.out.println("Mouse Clicked");
        }
        else
            super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}