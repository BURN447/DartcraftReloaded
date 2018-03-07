package burn447.dartcraftReloaded.Items.Tools;

import net.minecraft.item.ItemSpade;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemShovel extends ItemSpade {

    private String name;

    public ItemShovel(ToolMaterial material, String name){
        super(material);
        setUnlocalizedName(name);
        this.name = name;
    }
}
