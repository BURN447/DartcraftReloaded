package burn447.dartcraftReloaded.Items.Tools;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemShears extends net.minecraft.item.ItemShears {

    private String name;

    public ItemShears(ToolMaterial material, String name){
        //super(material);
        setUnlocalizedName(name);
        this.name = name;
    }

}
