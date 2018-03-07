package burn447.dartcraftReloaded.Items;

import burn447.dartcraftReloaded.Blocks.ModBlocks;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by BURN447 on 2/23/2018.
 */
public class ItemForceRod extends ItemBase {

    public ItemForceRod(String name){
        super(name);
        setHasSubtypes(true);
        setUnlocalizedName(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.getBlockState(pos) == Blocks.IRON_ORE.getDefaultState()){
            worldIn.setBlockState(pos, ModBlocks.orePower.getDefaultState());
        }

        return EnumActionResult.PASS;
    }



}
