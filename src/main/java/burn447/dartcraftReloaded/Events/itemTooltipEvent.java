package burn447.dartcraftReloaded.Events;

import burn447.dartcraftReloaded.Items.Tools.ItemForceSword;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class itemTooltipEvent {

    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        if(event.getItemStack().getItem() instanceof ItemForceSword) {
            NBTTagList enchants;
            if(event.getItemStack().getEnchantmentTagList() != null) {
                enchants = event.getItemStack().getEnchantmentTagList();
                for(int i = 0; i < enchants.tagCount(); i++) {
                    NBTTagCompound enchant = enchants.getCompoundTagAt(i);
                    if(enchant.getString("name") == "knockback") {
                        enchants.removeTag(i);
                    }
                }
            }
        }
    }
}
