package burn447.dartcraftReloaded.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by BURN447 on 2/14/2018.
 */
public class BlockForceLog extends BlockBase {

    public BlockForceLog(String name) {
        super(Material.WOOD, name);
        setHardness(2.5f);
        setResistance(12.5f);
        this.setRegistryName(name);
        this.setTranslationKey(name);
    }

    public BlockForceLog(String name, String oreName) {
        super(Material.WOOD, name);
        setHardness(2.5f);
        setResistance(12.5f);
        this.oreName = oreName;
        this.setRegistryName(name);
        this.setTranslationKey(name);
        super.initOreDict();
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

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        int i = 4;
        int j = 5;

        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
        {
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
            {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                {
                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                }
            }
        }
    }
}
