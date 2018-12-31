package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class ItemBase extends Item {

    protected String name;
    protected String oreName;

    public ItemBase(String name, String oreName) {
        this.name = name;
        this.oreName = oreName;
        setTranslationKey(name);
        setRegistryName(name);
    }

    public ItemBase(String name) {
        this.name = name;
        this.oreName = name;
        setTranslationKey(name);
        setRegistryName(name);
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public void initOreDict() {
        OreDictionary.registerOre(oreName, this);
    }
}
