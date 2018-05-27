package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.util.References;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by BURN447 on 5/20/2018.
 */
public class ToolModified implements IToolModifier {

    private final Set<Block> effectiveBlocks = new HashSet<>();
    private float attackDamage;
    private float attackSpeed;
    private Item.ToolMaterial toolMaterial;
    private float efficiency = 12.0F;

    public ToolModified(){
        //System.out.println("Initializing Tool Modified");
        //efficiency = 12.0F;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return efficiency;
    }

    @Override
    public int getItemEnchantibility() {
        return toolMaterial.getEnchantability();
    }

    @Override
    public boolean canApplyModifier(ItemStack stack, References.MODIFIERS mod) {
        return false;
    }

    @Override
    public Set<Block> getEffectiveBlocks() {
        return effectiveBlocks;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public Item.ToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public void setEfficiency(float newEfficiency) {
        efficiency = newEfficiency;
    }


}
