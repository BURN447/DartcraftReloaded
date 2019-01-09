package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.blocks.ModBlocks;
import burn447.dartcraftReloaded.capablilities.ToolModifier.ToolModProvider;
import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.DartUtils;
import burn447.dartcraftReloaded.util.References;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import gnu.trove.set.hash.THashSet;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;
import static burn447.dartcraftReloaded.util.DartUtils.isLog;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.*;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForceAxe extends ItemAxe {

    private static String name;

    public List<References.MODIFIERS> applicableModifers = new ArrayList<>();

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    public ItemForceAxe(String name) {
        super(dartcraftReloaded.forceToolMaterial, 8.0F, 8.0F);
        setApplicableModifers();
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(dartcraftReloaded.creativeTab);
        this.name = name;
        this.attackSpeed = -2.0F;
        this.attackDamage = 8.0F;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    public void setApplicableModifers() {
        applicableModifers.add(MOD_CHARGE);
        applicableModifers.add(MOD_CHARGEII);
        applicableModifers.add(MOD_HEAT);
        applicableModifers.add(MOD_LUCK);
        applicableModifers.add(MOD_GRINDING);
        applicableModifers.add(MOD_TOUCH);
        applicableModifers.add(MOD_STURDY);
        applicableModifers.add(MOD_REPAIR);
        applicableModifers.add(MOD_SPEED);
        applicableModifers.add(MOD_LUMBERJACK);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!stack.hasCapability(CAPABILITY_TOOLMOD, null))
            return new ToolModProvider(CAPABILITY_TOOLMOD, null);
        else
            return null;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasLumberJack()) {
            if (player != null) {
                if (DartUtils.isTree(player.getEntityWorld(), pos)) {
                    return fellTree(stack, pos, player);
                }
            }
        }
        return false;
    }

    public static boolean fellTree(ItemStack stack, BlockPos pos, EntityPlayer player){
        if(player.getEntityWorld().isRemote)
            return true;

        MinecraftForge.EVENT_BUS.register(new TreeChopTask(stack, pos, player, 10));
        return true;
    }

    public static class TreeChopTask{
        public final World world;
        public final EntityPlayer player;
        public final ItemStack tool;
        public final int blocksPerTick;

        public Queue<BlockPos> blocks = Lists.newLinkedList();
        public Set<BlockPos> visited = new THashSet<>();

        public TreeChopTask(ItemStack tool, BlockPos start, EntityPlayer player, int blocksPerTick) {
            this.world = player.getEntityWorld();
            this.player = player;
            this.tool = tool;
            this.blocksPerTick = blocksPerTick;

            this.blocks.add(start);
        }

        @SubscribeEvent
        public void chop(TickEvent.WorldTickEvent event) {
            if(event.side.isClient()) {
                finish();
                return;
            }
            // only if same dimension
            if(event.world.provider.getDimension() != world.provider.getDimension()) {
                return;
            }

            // setup
            int left = blocksPerTick;

            // continue running
            BlockPos pos;
            while(left > 0) {
                // completely done or can't do our job anymore?!
                if(blocks.isEmpty()) {
                    finish();
                    return;
                }

                pos = blocks.remove();
                if(!visited.add(pos)) {
                    continue;
                }

                // can we harvest the block and is effective?
                if(!isLog(world, pos)) {
                    continue;
                }

                // save its neighbours
                for(EnumFacing facing : new EnumFacing[] { EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST }) {
                    BlockPos pos2 = pos.offset(facing);
                    if(!visited.contains(pos2)) {
                        blocks.add(pos2);
                    }
                }

                // also add the layer above.. stupid acacia trees
                for(int x = 0; x < 3; x++) {
                    for(int z = 0; z < 3; z++) {
                        BlockPos pos2 = pos.add(-1 + x, 1, -1 + z);
                        if(!visited.contains(pos2)) {
                            blocks.add(pos2);
                        }
                    }
                }

                // break it, wooo!
                DartUtils.breakExtraBlock(tool, world, player, pos, pos);
                left--;
            }
        }

        private void finish() {
            // goodbye cruel world
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List lores, ITooltipFlag flagIn) {
        attatchInformation(stack, lores);
        super.addInformation(stack, worldIn, lores, flagIn);
    }

    static void attatchInformation(ItemStack stack, List lores) {
        if (stack.getCapability(CAPABILITY_TOOLMOD, null) != null) {
            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 12.0F) {
                lores.add("Speed I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 16.0F) {
                lores.add("Speed II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasHeat()) {
                lores.add("Heat");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 1.5F) {
                lores.add("Force I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 2.0F) {
                lores.add("Force II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasGrinding()) {
                lores.add("Grinding");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasTouch()) {
                lores.add("Silk Touch");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 12.0F) {
                lores.add("Damage I");
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 16.0F) {
                lores.add("Damage II");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasLumberJack()) {
                lores.add("LumberJack");
            }

            if (stack.getCapability(CAPABILITY_TOOLMOD, null).hasEnder()) {
                lores.add("Ender");
            }

            if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(4))
                lores.add("Bleeding IV");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(3))
                lores.add("Bleeding III");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2))
                lores.add("Bleeding II");
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1))
                lores.add("Bleeding I");

            if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBane())
                lores.add("Bane - Does not prevent teleportaion in rain currently - Bug? Not sure why");
        }
    }
}
