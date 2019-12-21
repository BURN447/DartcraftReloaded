package burn447.dartcraftReloaded.client.gui.belt;

import burn447.dartcraftReloaded.Items.ItemForcePack;
import burn447.dartcraftReloaded.container.ContainerItemForcePack;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIForceBelt extends GuiContainer {
    private ItemStack fp;

    private ResourceLocation TEXTURE = new ResourceLocation(References.modId, "textures/gui/container/forcepack.png");

    public GUIForceBelt(IInventory playerInv, ItemStack fp) {
        super(new ContainerItemForcePack(playerInv, fp));

        this.xSize = 176;
        this.ySize = 208;

        this.fp = fp;
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
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);

    }
}
