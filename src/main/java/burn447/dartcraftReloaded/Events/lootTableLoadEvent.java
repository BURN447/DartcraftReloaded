package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.lootTables.DCRLootTables;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class lootTableLoadEvent {

    @SubscribeEvent
    public void onLootTableLoadEvent(LootTableLoadEvent event) {
        if(event.getName().equals(LootTableList.ENTITIES_BAT)) {

            LootCondition[] noCondition = new LootCondition[0];

            LootPool pool = event.getTable().getPool("main");

            if(pool == null) {
                pool = new LootPool(new LootEntry[0], noCondition, new RandomValueRange(0, 2), new RandomValueRange(0), "main");
                event.getTable().addPool(pool);
            }

            LootFunction amount = new SetCount(noCondition, new RandomValueRange(1, 2));
            pool.addEntry(new LootEntryItem(ModItems.claw, 1, 0, new LootFunction[]{amount}, noCondition, References.modId + ":claws"));
        }
    }
}
