package burn447.dartcraftReloaded.client;

import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class tabDartcraft extends CreativeTabs {

    public tabDartcraft(){
        super(References.modId);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.gemForceGem);
    }
}
