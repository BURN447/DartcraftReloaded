package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;

/**
 * Created by BURN447 on 6/2/2018.
 */
public class ItemFortune extends ItemBase {

    private String name;

    public ItemFortune(String name) {
        super(name);
        this.name = name;
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }
}
