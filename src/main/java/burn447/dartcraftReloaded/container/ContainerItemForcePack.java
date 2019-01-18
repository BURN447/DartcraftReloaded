package burn447.dartcraftReloaded.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerItemForcePack extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    public ContainerItemForcePack(IInventory playerInv) {

        int xPosC = 17;
        int yPosC = 20;

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 8; j++) {
                this.addSlotToContainer(new Slot(playerInv, i * j, xPosC + j * 18, yPosC + i * 18 ));
            }
        }

        //Player Inventory
        int xPos = 8;
        int yPos = 127;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }
    }


}
