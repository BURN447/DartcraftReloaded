package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.item.ItemPickaxe;
import static burn447.dartcraftReloaded.dartcraftReloaded.forceToolMaterial;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForcePickaxe extends ItemPickaxe {

    private static String name;

    public ItemForcePickaxe(String name) {
       super(forceToolMaterial);
       this.setRegistryName(name);
       this.setUnlocalizedName(name);
       this.setCreativeTab(dartcraftReloaded.creativeTab);
       this.efficiency = 15F;
       this.attackDamage = 2.0F;
       this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }
}
