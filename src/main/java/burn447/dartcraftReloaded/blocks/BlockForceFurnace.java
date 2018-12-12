package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.BlockFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockForceFurnace extends BlockFurnace {

    protected BlockForceFurnace(boolean isBurning) {
        super(isBurning);
        this.setRegistryName("forceFurnace");
        this.setUnlocalizedName("forceFurnace");
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, "forceFurnace");
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }


}
