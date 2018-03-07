package burn447.dartcraftReloaded.Items.Tools;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemPickaxe extends net.minecraft.item.ItemPickaxe {

    private String name;

    public ItemPickaxe(ToolMaterial material, String name){
        super(material);
        setUnlocalizedName(name);
        this.name = name;
    }
}
