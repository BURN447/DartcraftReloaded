package burn447.dartcraftReloaded.blocks;

import burn447.dartcraftReloaded.Handlers.DCRGUIHandler;
import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.tileEntity.Infuser.TileEntityInfuser;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class BlockInfuser extends BlockBase {

    public BlockInfuser(String name) {
        super(Material.ROCK, name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.setRegistryName(name);
        this.setTranslationKey(name);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }


    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityInfuser te = (TileEntityInfuser) world.getTileEntity(pos);
        if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {

            IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            for (int slot = 0; slot < handler.getSlots() - 1; slot++) {
                ItemStack stack = handler.getStackInSlot(slot);
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
            super.breakBlock(world, pos, state);
        }
    }


    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityInfuser();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(dartcraftReloaded.instance, DCRGUIHandler.INFUSER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}

