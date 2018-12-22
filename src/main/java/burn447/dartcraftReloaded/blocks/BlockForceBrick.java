package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Created by BURN447 on 7/23/2018.
 */
public class BlockForceBrick extends BlockColored {

    public BlockForceBrick(){
        super(Material.ROCK);
        this.setHardness(50.0F);
        this.setResistance(200.0F);
        this.setRegistryName("forceBrick");
        //this.setUnlocalizedName("forceBrick");
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, "forceBrick");
    }
}
