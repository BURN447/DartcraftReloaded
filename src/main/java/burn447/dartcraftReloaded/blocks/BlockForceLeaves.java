package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.dartcraftReloaded;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.core.config.Order;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;


public class BlockForceLeaves extends BlockLeaves {

    public String name;

    public BlockForceLeaves(String name) {
        this.setHardness(0.3f);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.name = name;
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.createItemBlock();

        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, false).withProperty(DECAYABLE, true));
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return 25;
    }

    // sapling item
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.forceSapling);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if (worldIn.rand.nextInt(chance) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.nuggetForce, 1, 0));
        }
    }

    @Nonnull
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0;

        if (!(Boolean) state.getValue(DECAYABLE))
        {
            i |= 4;
        }

        if ((Boolean) state.getValue(CHECK_DECAY))
        {
            i |= 8;
        }

        return i;
    }

    @Nonnull
    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        throw new UnsupportedOperationException(); // unused by our code.
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return Lists.newArrayList(getSilkTouchDrop(state));
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.isOpaqueCube(state);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side) {
        BlockPos neighborPos = pos.offset(side);
        if (isOpaqueCube(state) && access.getBlockState(neighborPos).getBlock() == this) {
            return false;
        }
        return !access.getBlockState(neighborPos).doesSideBlockRendering(access, neighborPos, side.getOpposite());
    }

    public BlockRenderLayer getRenderLayer() {
        return Blocks.LEAVES.getRenderLayer();
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 60;
    }


}
