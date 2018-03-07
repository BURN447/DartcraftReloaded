package burn447.dartcraftReloaded.Items.Tools;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemAxe extends net.minecraft.item.ItemAxe {

    private String name;

    public ItemAxe(ToolMaterial material, String name){
        super(material);
        setUnlocalizedName(name);
        this.name = name;
    }


}
