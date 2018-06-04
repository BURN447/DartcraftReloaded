package burn447.dartcraftReloaded.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by BURN447 on 3/31/2018.
 */
public class Utils {

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
}
