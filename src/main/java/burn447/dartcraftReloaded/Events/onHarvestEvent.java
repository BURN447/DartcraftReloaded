package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ListIterator;
import java.util.Random;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 6/1/2018.
 */
public class onHarvestEvent {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        ItemStack heldItem;
        Random random = new Random();
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
                                if(heldItem.getCapability(CAPABILITY_TOOLMOD, null).hasAnyLuck()) {
                                    smelted.setCount(smelted.getCount() * random.nextInt(heldItem.getCapability(CAPABILITY_TOOLMOD, null).luckLevel() + 1) + 1);
                                } else {
                                    smelted.setCount(drop.getCount());
                                }
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
