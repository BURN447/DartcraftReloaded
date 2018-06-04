package burn447.dartcraftReloaded.Events;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ListIterator;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/1/2018.
 */
public class onHarvestEvent {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        ItemStack heldItem;
        if(event.getHarvester() != null){
            heldItem = event.getHarvester().inventory.getCurrentItem();
            if (heldItem != ItemStack.EMPTY) {
                if (heldItem.getCapability(CAPABILITY_TOOLMOD, null) != null) {
                    if (heldItem.getCapability(CAPABILITY_TOOLMOD, null).hasHeat()) {
                        ListIterator<ItemStack> iter = event.getDrops().listIterator();
                        while (iter.hasNext()) {
                            ItemStack drop = iter.next();
                            ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(drop);
                            if (!smelted.isEmpty()) {
                                smelted = smelted.copy();
                                smelted.setCount(drop.getCount());

                                //TODO: Add Fortune Integration

                                iter.set(smelted);

                                float xp = FurnaceRecipes.instance().getSmeltingExperience(smelted);
                                if (xp < 1 && Math.random() < xp) {
                                    xp += 1F;
                                }
                                if (xp >= 1F) {
                                    event.getState().getBlock().dropXpOnBlockBreak(event.getWorld(), event.getPos(), (int) xp);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
