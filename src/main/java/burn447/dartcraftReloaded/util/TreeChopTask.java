package burn447.dartcraftReloaded.util;


import com.google.common.collect.Lists;
import gnu.trove.set.hash.THashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Queue;
import java.util.Set;

import static burn447.dartcraftReloaded.util.DartUtils.isLog;

/**
 * Created by BURN447 on 6/6/2018.
 */
public class TreeChopTask {

    public final World world;
    public final EntityPlayer player;
    public final ItemStack tool;
    public final int blocksPerTick;

    public Queue<BlockPos> blocks = Lists.newLinkedList();
    public Set<BlockPos> visited = new THashSet<BlockPos>();

    public TreeChopTask(ItemStack tool, BlockPos start, EntityPlayer player, int blocksPerTick){
        this.world = player.getEntityWorld();
        this.player = player;
        this.tool = tool;
        this.blocksPerTick = blocksPerTick;

        this.blocks.add(start);
    }

    @SubscribeEvent
    public void chop(TickEvent.WorldTickEvent event){
        if(event.side.isClient()){
            finish();
            return;
        }

        if(event.world.provider.getDimension() != world.provider.getDimension()){
            return;
        }

        int left = blocksPerTick;

        BlockPos pos;
        while(left > 0){
            if(blocks.isEmpty()){
                finish();
                return;
            }

            pos = blocks.remove();
            if(!visited.add(pos)){
                continue;
            }

            if(!isLog(world, pos)){
                continue;
            }

            for(EnumFacing facing : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST}){
                BlockPos pos2 = pos.offset(facing);
                if(!visited.contains(pos2)){
                    blocks.add(pos2);
                }
            }

            for(int x = 0; x < 3; x++){
                for(int z = 0; z < 3; z++){
                    BlockPos pos2 = pos.add(-1 + x, 1, -1 + z);
                    if(!visited.contains(pos2)){
                        blocks.add(pos2);
                    }
                }
            }
            left--;
        }
    }

    private void finish(){
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
