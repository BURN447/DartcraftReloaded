package burn447.dartcraftReloaded.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by BURN447 on 6/25/2018.
 */
public class ProgressBar extends Gui {

    private ResourceLocation texture;
    private ProgressBarDirection direction;
    private int positionX, positionY;
    private int width, height;
    private int textureX, textureY;

    private float min, max = 0;


    /**
     *
     * @param texture       Texture of the Bar
     * @param direction     Direction for the Bar to move
     * @param width         Width of Progress Bar
     * @param height        Height of Progress Bar
     * @param posX          X Position
     * @param posY          Y Position
     * @param textureX      X Position of Texture
     * @param textureY      Y Position of Texture
     */
    public ProgressBar(ResourceLocation texture, ProgressBarDirection direction, int width, int height, int posX, int posY, int textureX, int textureY){
        this.texture = texture;
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.positionX = posX;
        this.positionY = posY;
        this.textureX = textureX;
        this.textureY = textureY;
    }

    /**
     *
     * @param min           Minimum value (Default: 0)
     * @return              Edited instance
     */
    public ProgressBar setMin(int min){
        this.min = min;
        return this;
    }

    public ProgressBar setMax(int max){
        this.min = max;
        return this;
    }

    private int getAdjustedWidth() {
        return (int) (min != 0 && max != 0 ? min / max * width : 0);
    }

    private int getAdjustedHeight() {
        return (int) (min != 0 && max != 0 ? min / max * height : 0);
    }

    public void draw(Minecraft mc) {
        GlStateManager.color(232.0F, 244.0F, 66.0F, 12.0F);
        mc.getTextureManager().bindTexture(texture);
        switch (direction) {
            case DIAGONAL_UP_LEFT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width - getAdjustedWidth(), height - getAdjustedHeight());
                break;
            case DIAGONAL_UP_RIGHT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                this.drawTexturedModalRect(positionX + getAdjustedWidth(), positionY, positionX + getAdjustedWidth(), positionY, width - getAdjustedWidth(), height - getAdjustedHeight());
                break;
            case DIAGONAL_DOWN_LEFT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                this.drawTexturedModalRect(positionX, positionY + getAdjustedHeight(), positionX, positionY + getAdjustedHeight(), width - getAdjustedWidth(), height - getAdjustedHeight());
                break;
            case DIAGONAL_DOWN_RIGHT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, getAdjustedWidth(), getAdjustedHeight());
                break;
            case DOWN_TO_UP:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width, height - getAdjustedHeight());
                break;
            case LEFT_TO_RIGHT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, getAdjustedWidth(), height);
                break;
            case RIGHT_TO_LEFT:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width - getAdjustedWidth(), height);
                break;
            case UP_TO_DOWN:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, getAdjustedHeight());
                break;
            default:
                this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
                break;
        }
    }



    public enum ProgressBarDirection {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, UP_TO_DOWN, DOWN_TO_UP, DIAGONAL_UP_RIGHT, DIAGONAL_UP_LEFT, DIAGONAL_DOWN_RIGHT, DIAGONAL_DOWN_LEFT;
    }
}
