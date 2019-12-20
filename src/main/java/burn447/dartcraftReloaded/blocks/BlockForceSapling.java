
package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;

import java.util.Random;


@SuppressWarnings("deprecation")
public class BlockForceSapling extends BlockBush implements IGrowable, IPlantable {

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);


    public BlockForceSapling() {
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.setTranslationKey("force_sapling");
        this.setRegistryName("force_sapling");
        this.setSoundType(SoundType.PLANT);
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, "force_sapling");
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public String getLocalizedName() {
        return I18n.translateToLocal(this.getTranslationKey() + "." + this.getTranslationKey() + ".name");
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(world, pos, state, rand);
            }
        }
    }

    public void grow(World world, BlockPos pos, IBlockState state, Random rand) {
        this.generateTree(world, pos, state, rand);
    }

    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {

        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, pos))
            return;

        WorldGenerator worldgenerator = new WorldGenTrees(true, 5, ModBlocks.forceLog.getStateFromMeta(0), ModBlocks.forceLeaves.getDefaultState(), false);

        world.setBlockToAir(pos);

        if (!worldgenerator.generate(world, rand, pos)) {
            world.setBlockState(pos, state, 2);
        }
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return (double)world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        this.grow(world, pos, state, rand);
    }

    @Override
    public Material getMaterial(IBlockState state) {
        return Material.PLANTS;
    }


}