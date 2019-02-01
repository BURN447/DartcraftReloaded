package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class lootTableLoadEvent {

    @SubscribeEvent
    public void onLootTableLoadEvent(LootTableLoadEvent event) {
        if(event.getName().equals(LootTableList.ENTITIES_BAT)) {
            final LootPool pool = event.getTable().getPool("main");

            if(pool != null) {
                System.out.println("Loaded Bat Loot Table");
                pool.addEntry(new LootEntryItem(ModItems.claw, 1, 2, new LootFunction[0], new LootCondition[0], References.modId + ":batLoot"));
            }
        }
    }
}
