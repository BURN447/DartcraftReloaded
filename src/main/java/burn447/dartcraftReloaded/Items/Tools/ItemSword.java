package burn447.dartcraftReloaded.Items.Tools;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemSword extends net.minecraft.item.ItemSword {

    private String name;

    public ItemSword(ToolMaterial material, String name){
        super(material);
        setUnlocalizedName(name);
        this.name = name;
    }
}
