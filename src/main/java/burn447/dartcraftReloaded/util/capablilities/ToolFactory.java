package burn447.dartcraftReloaded.util.capablilities;

import burn447.dartcraftReloaded.Items.Tools.ItemToolBase;
import burn447.dartcraftReloaded.util.Tools.ToolModified;
import net.minecraft.item.ItemStack;

public class ToolFactory {

    public static ToolModified getToolModified(ItemStack stack){
        if(stack.getItem() instanceof ItemToolBase)
            return new ToolModified();
        else
            return null;
    }
}
