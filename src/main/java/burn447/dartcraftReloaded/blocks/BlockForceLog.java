package burn447.dartcraftReloaded.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by BURN447 on 2/14/2018.
 */
public class BlockForceLog extends BlockBase {

    public BlockForceLog(String name) {
        super(Material.WOOD, name);
        setHardness(2.5f);
        setResistance(12.5f);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public BlockForceLog setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public BlockStateContainer getBlockState() {
        return super.getBlockState();
    }
}
