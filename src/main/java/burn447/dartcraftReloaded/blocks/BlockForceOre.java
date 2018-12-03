package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.Items.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class BlockForceOre extends BlockBase {

    public BlockForceOre(String name) {
        super(Material.ROCK, name);
        setHardness(3f);
        setResistance(5f);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public BlockForceOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return ModItems.gemForceGem;
    }

    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(2);
    }



}
