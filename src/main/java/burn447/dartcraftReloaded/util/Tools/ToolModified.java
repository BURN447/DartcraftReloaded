package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.Items.Tools.ItemToolBase;
import burn447.dartcraftReloaded.util.References.*;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BURN447 on 3/18/2018.
 */
public class ToolModified implements IToolModifier {

    public void addModifier(MODIFIERS mod, ITEMTYPE tool, ItemToolBase toolItem){
        switch(tool){
            case TOOL_AXE:
                axeMod(mod, toolItem);
                break;
            case TOOL_BOW:
                bowMod(mod, toolItem);
                break;
            case TOOL_CLAWS:
                clawMod(mod, toolItem);
                break;
            case TOOL_PICK:
                pickMod(mod, toolItem);
                break;
            case TOOL_SHEARS:
                shearMod(mod, toolItem);
                break;
            case TOOL_SPADE:
                spadeMod(mod, toolItem);
                break;
            case ARMOR_HELM:
                helmMod(mod, toolItem);
                break;
            case ARMOR_LEGS:
                legMod(mod, toolItem);
                break;
            case TOOL_SWORD:
                swordMod(mod, toolItem);
                break;
            case ARMOR_BOOTS:
                bootMod(mod, toolItem);
                break;
            case ARMOR_CHEST:
                chestMod(mod, toolItem);
                break;
        }
    }

    public void axeMod(MODIFIERS mod, ItemToolBase toolItem){
        switch(mod){
            case MOD_SPEED:
                break;
            case MOD_LUMBERJACK:
                break;
            case MOD_HEAT:
                break;
            case MOD_LUCK:
                break;
            case MOD_GRINDING:
                break;
            case MOD_TOUCH:
                break;
            case MOD_STURDY:
                break;
            case MOD_REPAIR:
                break;
        }
    }
    public void bowMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void clawMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void pickMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void shearMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void spadeMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void helmMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void legMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void swordMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void bootMod(MODIFIERS mod, ItemToolBase toolItem){

    }
    public void chestMod(MODIFIERS mod, ItemToolBase toolItem){

    }

    @Override
    public boolean canApplyModifer(ItemStack stack, MODIFIERS mod) {
        return false;
    }

    @Override
    public ItemStack applyModifer(ItemStack stack, MODIFIERS mod) {
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }
}
