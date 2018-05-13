package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.item.ItemSword;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForceSword extends ItemSword {

    private static String name;

    public ItemForceSword(String name) {
        super(ToolMaterial.DIAMOND);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }
}
