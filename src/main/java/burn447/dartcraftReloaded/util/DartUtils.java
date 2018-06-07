package burn447.dartcraftReloaded.util;

import burn447.dartcraftReloaded.Blocks.BlockForceLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class DartUtils {

    public static Logger logger;

    public static Logger getLogger(){
        if(logger == null){
            logger = LogManager.getFormatterLogger(References.modId);
        }
        return logger;
    }

    public static void removeEnchant(Enchantment enchantment, ItemStack stack){
        Map<Enchantment, Integer> enchantMap = EnchantmentHelper.getEnchantments(stack);
        if(enchantMap.containsKey(enchantment)){
            enchantMap.remove(enchantment);
        }

        EnchantmentHelper.setEnchantments(enchantMap, stack);
    }

    public static boolean isTree(World world, BlockPos origin){
        BlockPos pos = null;
        Stack<BlockPos> candidates = new Stack<>();
        candidates.add(origin);

        while(!candidates.isEmpty()) {
            BlockPos candidate = candidates.pop();
            if((pos == null || candidate.getY() > pos.getY()) && isLog(world, candidate)) {
                pos = candidate.up();
                // go up
                while(isLog(world, pos)) {
                    pos = pos.up();
                }
                // check if we still have a way diagonally up
                candidates.add(pos.north());
                candidates.add(pos.east());
                candidates.add(pos.south());
                candidates.add(pos.west());
            }
        }

        // not even one match, so there were no logs.
        if(pos == null) {
            return false;
        }

        // check if there were enough leaves around the last position
        // pos now contains the block above the topmost log
        // we want at least 5 leaves in the surrounding 26 blocks
        int d = 3;
        int o = -1; // -(d-1)/2
        int leaves = 0;
        for(int x = 0; x < d; x++) {
            for(int y = 0; y < d; y++) {
                for(int z = 0; z < d; z++) {
                    BlockPos leaf = pos.add(o + x, o + y, o + z);
                    IBlockState state = world.getBlockState(leaf);
                    if(state.getBlock().isLeaves(state, world, leaf)) {
                        if(++leaves >= 5) {
                            return true;
                        }
                    }
                }
            }
        }

        // not enough leaves. sorreh
        return false;
    }

    public static boolean isLog(World world, BlockPos pos){
        if(world.getBlockState(pos).getBlock().isWood(world, pos) || world.getBlockState(pos).getBlock() instanceof BlockForceLog){
            return true;
        }
        return false;
    }

    public static boolean fellTree(ItemStack stack, BlockPos pos, EntityPlayer player){
        if(player.getEntityWorld().isRemote)
            return true;

        MinecraftForge.EVENT_BUS.register(new TreeChopTask(stack, pos, player, ((int) stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency())));
        return true;
    }
}
