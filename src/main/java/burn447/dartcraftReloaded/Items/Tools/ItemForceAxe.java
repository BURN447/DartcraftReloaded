package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.DartUtils;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;
import static burn447.dartcraftReloaded.util.DartUtils.fellTree;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.*;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_REPAIR;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_SPEED;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForceAxe extends ItemToolBase {

    private static String name;

    public List<References.MODIFIERS> applicableModifers = new ArrayList<>();

    public ItemForceAxe(String name) {
        super(name);
        setApplicableModifers();
        this.name = name;
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
        return super.initCapabilities(stack, nbt);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {

        EntityPlayer player = null;
        if (entityLiving instanceof EntityPlayer) {
            player = (EntityPlayer) entityLiving;
        }
        if (player != null) {
            if (DartUtils.isTree(worldIn, pos)) {
                return fellTree(stack, pos, player);
            }
        }
        return false;
    }
}
