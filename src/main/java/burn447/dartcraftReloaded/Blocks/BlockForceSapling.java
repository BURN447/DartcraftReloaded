package burn447.dartcraftReloaded.Blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.world.WorldGenForceTree;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

/**
 * Created by BURN447 on 2/5/2018.
 */
public class BlockForceSapling extends BlockBush implements IGrowable {

    public static final PropertyEnum<BlockPlanks.EnumType> TYPE = PropertyEnum.<BlockPlanks.EnumType>create("type", BlockPlanks.EnumType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);



    public BlockForceSapling(String name){
        setTickRandomly(true);
        setCreativeTab(dartcraftReloaded.creativeTab);
        setUnlocalizedName(name);
        setRegistryName("forceSapling");
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient){
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state){
        this.generateTree(worldIn, pos, state, rand);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state){
        return true;
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {

    }
}
