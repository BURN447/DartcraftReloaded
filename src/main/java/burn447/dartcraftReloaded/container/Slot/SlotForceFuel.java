package burn447.dartcraftReloaded.container.Slot;

import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotForceFuel extends Slot {

    public SlotForceFuel(IInventory inventoryIn, int slotIndex, int xPos, int yPos) {
        super(inventoryIn, slotIndex, xPos, yPos);
    }

    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ModItems.gemForceGem;
    }
}
