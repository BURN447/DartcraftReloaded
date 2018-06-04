package burn447.dartcraftReloaded.Items.Tools;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.References;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import sun.security.pkcs11.wrapper.CK_AES_CTR_PARAMS;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.*;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_REPAIR;
import static burn447.dartcraftReloaded.util.References.MODIFIERS.MOD_SPEED;

/**
 * Created by BURN447 on 5/13/2018.
 */
public class ItemForceSword extends ItemToolBase {

    private static String name;

    public List<References.MODIFIERS> applicableModifers = new ArrayList<>();

    public ItemForceSword(String name) {
        super(name);
        setApplicableModifers();
        this.name = name;
    }

    public void registerItemModel() {
        dartcraftReloaded.proxy.registerItemRenderer(this, 0, name);
    }

    public void setApplicableModifers() {
        applicableModifers.add(MOD_DAMAGE);
        applicableModifers.add(MOD_FREEZING);
        applicableModifers.add(MOD_HEAT);
        applicableModifers.add(MOD_LUCK);
        applicableModifers.add(MOD_WING);
        applicableModifers.add(MOD_BANE);
        applicableModifers.add(MOD_BLEED);
        applicableModifers.add(MOD_LIGHT);
        applicableModifers.add(MOD_REPAIR);
        applicableModifers.add(MOD_SOUL);
        applicableModifers.add(MOD_TREASURE);
        applicableModifers.add(MOD_IMPERVIOUS);
        applicableModifers.add(MOD_FORCE);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.WEB;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        Vec3d look = attacker.getLookVec().normalize();

        double knockback = stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback();

        target.addVelocity(look.x * knockback, look.y * knockback, look.z * knockback);

        stack.damageItem(1, attacker);
        return true;
    }

    public float getAttackDamage() {
        return dartcraftReloaded.forceToolMaterial.getAttackDamage();
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.getAttackDamage(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }

}
