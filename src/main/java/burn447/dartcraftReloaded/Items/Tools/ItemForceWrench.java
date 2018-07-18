package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.Items.ItemBase;
import burn447.dartcraftReloaded.capablilities.ForceWrench.ForceWrenchProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_FORCEWRENCH;

/**
 * Created by BURN447 on 3/6/2018.
 */
public class ItemForceWrench extends ItemBase {

    public ItemForceWrench(String name){
        super(name);
        setUnlocalizedName(name);
        setCreativeTab(dartcraftReloaded.creativeTab);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (player.getHeldItem(hand).getItem() instanceof ItemForceWrench) {
            ItemStack heldWrench = player.getHeldItem(hand);

            if (world.getTileEntity(pos) instanceof TileEntity && !heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).canStoreBlock()) {
                return serializeNBT(world, pos, player, hand);
            } else if(heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).canStoreBlock())
                placeBlockFromWrench(world, pos, player, hand);
        }
        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_FORCEWRENCH, null))
            return new ForceWrenchProvider(CAPABILITY_FORCEWRENCH, null);
        else
            return null;
    }

    private EnumActionResult serializeNBT(World world, BlockPos pos, EntityPlayer player, EnumHand hand){
        ItemStack heldWrench = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if(heldWrench.hasCapability(CAPABILITY_FORCEWRENCH, null)) {
            ResourceLocation blockName = state.getBlock().getRegistryName();
            int metadata = state.getBlock().getMetaFromState(state);
            TileEntity tileEntity = world.getTileEntity(pos);
            NBTTagCompound nbt = new NBTTagCompound();

            if(tileEntity != null){
                tileEntity.writeToNBT(nbt);
                //System.out.println(nbt + "\n" + world.getBlockState(pos));
                heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).storeBlockNBT(nbt);
                heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).storeBlockState(world.getBlockState(pos));
                world.removeTileEntity(pos);
                world.setBlockToAir(pos);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    private EnumActionResult placeBlockFromWrench(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
        ItemStack heldWrench = player.getHeldItem(hand);
        if(heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).getStoredBlockState() != null) {
            NBTTagCompound tileCmp = heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).getStoredBlockNBT();
            IBlockState state = heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).getStoredBlockState();
            TileEntity te = TileEntity.create(world, tileCmp);
            te.setPos(pos.offset(EnumFacing.UP));
            world.setBlockState(pos.offset(EnumFacing.UP), state);
            world.setTileEntity(pos.offset(EnumFacing.UP), te);
            heldWrench.getCapability(CAPABILITY_FORCEWRENCH, null).clearBlockStorage();
            System.out.println(tileCmp);
        }
        return EnumActionResult.SUCCESS;
    }
}
