package burn447.dartcraftReloaded.util;

import burn447.dartcraftReloaded.Handlers.DCRPacketHandler;
import burn447.dartcraftReloaded.blocks.BlockForceLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ForgeEventFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;
import java.util.Stack;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class DartUtils {

    public static Logger logger;
    public static final String RESOURCE = References.modId.toLowerCase(Locale.US);

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

    //Credit to Slimeknights for this code until I can logic through it on my own
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
        if(world.getBlockState(pos).getBlock() instanceof BlockLog || world.getBlockState(pos).getBlock() instanceof BlockForceLog)
            return true;
        else
            return false;
    }

    public static void breakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos pos, BlockPos refPos) {
        if(!canBreakExtraBlock(stack, world, player, pos, refPos)) {
            return;
        }

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        stack.onBlockDestroyed(world, state, pos, player);

        // server sided handling
        if(!world.isRemote) {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, pos);
            if(xp == -1) {
                return;
            }

            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            TileEntity tileEntity = world.getTileEntity(pos);
            // ItemInWorldManager.removeBlock
            if(block.removedByPlayer(state, world, pos, player, true)) { // boolean is if block can be harvested, checked above
                block.onBlockHarvested(world, pos, state, player);
                block.harvestBlock(world, player, pos, state, tileEntity, stack);
                block.dropXpOnBlockBreak(world, pos, xp);
            }

            // always send block update to client
            DCRPacketHandler.sendPacket(player, new SPacketBlockChange(world, pos));
        }
        // client sided handling
        else {
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playBroadcastSound(2001, pos, Block.getStateId(state));
            if(block.removedByPlayer(state, world, pos, player, true)) {
                block.onBlockHarvested(world, pos, state, player);
            }
            // callback to the tool
            stack.onBlockDestroyed(world, state, pos, player);

            if(stack.getCount() == 0 && stack == player.getHeldItemMainhand()) {
                ForgeEventFactory.onPlayerDestroyItem(player, stack, EnumHand.MAIN_HAND);
                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            }

            // send an update to the server, so we get an update back
            //if(PHConstruct.extraBlockUpdates)
            NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getConnection();
            assert netHandlerPlayClient != null;
            netHandlerPlayClient.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, Minecraft
                    .getMinecraft().objectMouseOver.sideHit));
        }
    }

    private static boolean canBreakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos pos, BlockPos refPos) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if(world.isAirBlock(pos)) {
            return false;
        }

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // only effective materials
        //TODO: Check for Effective

        IBlockState refState = world.getBlockState(refPos);
        float refStrength = ForgeHooks.blockStrength(refState, player, world, refPos);
        float strength = ForgeHooks.blockStrength(state, player, world, pos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if(!ForgeHooks.canHarvestBlock(block, player, world, pos) || refStrength / strength > 10f) {
            return false;
        }

        // From this point on it's clear that the player CAN break the block

        if(player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, pos, state, player);
            if(block.removedByPlayer(state, world, pos, player, false)) {
                block.onBlockHarvested(world, pos, state, player);
            }

            // send update to client
            if(!world.isRemote) {
                DCRPacketHandler.sendPacket(player, new SPacketBlockChange(world, pos));
            }
            return false;
        }
        return true;
    }

    public static boolean isFakePlayer(Entity player){
        return (player instanceof FakePlayer);
    }

    public static String resource(String res) {
        return String.format("%s:%s", RESOURCE, res);
    }

    public static ResourceLocation getResource(String res) {
        return new ResourceLocation(RESOURCE, res);
    }

}
