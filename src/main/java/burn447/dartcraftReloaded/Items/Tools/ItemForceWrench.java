package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.Items.ItemBase;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EnumActionResult.PASS;
    }
}
