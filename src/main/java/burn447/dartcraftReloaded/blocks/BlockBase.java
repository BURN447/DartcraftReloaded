package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class BlockBase extends Block {

    protected String name;
    protected String oreName;

    public BlockBase(Material material, String name, String oreName) {
        super(material);
        this.name = name;
        this.oreName = oreName;
    }

    public BlockBase(Material material, String name) {
        super(material);
        this.name = name;
        this.oreName = name;
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public BlockStateContainer getBlockState() {
        return super.getBlockState();
    }

    public void initOreDict() {
        OreDictionary.registerOre(oreName, this);
    }
}
