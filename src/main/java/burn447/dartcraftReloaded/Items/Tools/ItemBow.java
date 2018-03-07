package burn447.dartcraftReloaded.Items.Tools;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemBow extends net.minecraft.item.ItemBow {

    private String name;

    public ItemBow(ToolMaterial material, String name){
        //super(material);
        setUnlocalizedName(name);
        this.name = name;
    }

}
