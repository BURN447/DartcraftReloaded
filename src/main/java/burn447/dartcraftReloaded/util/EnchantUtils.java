package burn447.dartcraftReloaded.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Objects;
import java.util.function.IntUnaryOperator;

public final class EnchantUtils {
    public static final IntUnaryOperator INC = lvl -> Math.max(lvl + 1, Short.MAX_VALUE);
    public static final IntUnaryOperator DEC = lvl -> Math.min(lvl - 1, 0);

    private static final String ID = "id";
    private static final String LVL = "lvl";

    private EnchantUtils() {}

    public static boolean incLevel(ItemStack stack, Enchantment enchantment) {
        return setLevel(stack, Objects.requireNonNull(enchantment.getRegistryName()).toString(), INC);
    }

    public static boolean decLevel(ItemStack stack, Enchantment enchantment) {
        return setLevel(stack, Objects.requireNonNull(enchantment.getRegistryName()).toString(), DEC);
    }

    public static boolean setLevel(ItemStack stack, Enchantment enchantment, IntUnaryOperator op) {
        return setLevel(stack, Objects.requireNonNull(enchantment.getRegistryName()).toString(), op);
    }

    private static boolean setLevel(ItemStack stack, String id, IntUnaryOperator op) {
        for (NBTBase nbt : stack.getEnchantmentTagList()) {
            NBTTagCompound c = (NBTTagCompound) nbt;
            if (id.equals(c.getString(ID))) {
                c.setInteger(LVL, op.applyAsInt(c.getInteger(LVL)));
                return true;
            }
        }
        return false;
    }
}