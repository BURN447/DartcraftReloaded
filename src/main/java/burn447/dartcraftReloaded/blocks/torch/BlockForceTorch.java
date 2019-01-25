package burn447.dartcraftReloaded.blocks.torch;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockForceTorch extends BlockTorch {

    String name;

    public BlockForceTorch(String name) {
        this.name = name;
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.setLightLevel(0.9375F);
        this.setSoundType(SoundType.WOOD);
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
