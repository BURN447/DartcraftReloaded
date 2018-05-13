package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.item.ItemSpade;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForceShovel extends ItemSpade {

    private static String name;

    public ItemForceShovel(String name) {
        super(ToolMaterial.DIAMOND);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.efficiency = 15F;
        this.attackDamage = 3.0F;
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }
}
