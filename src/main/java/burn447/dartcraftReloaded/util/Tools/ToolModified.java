package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.util.References.*;
import burn447.dartcraftReloaded.util.capablilities.ITool;

/**
 * Created by BURN447 on 3/18/2018.
 */
public class ToolModified implements ITool {

    public void addModifier(MODIFIERS mod, ITEMTYPE tool){
        switch(tool){
            case TOOL_AXE:
                axeMod(mod);
                break;
            case TOOL_BOW:
                bowMod(mod);
                break;
            case TOOL_CLAWS:
                clawMod(mod);
                break;
            case TOOL_PICK:
                pickMod(mod);
                break;
            case TOOL_SHEARS:
                shearMod(mod);
                break;
            case TOOL_SPADE:
                spadeMod(mod);
                break;
            case ARMOR_HELM:
                helmMod(mod);
                break;
            case ARMOR_LEGS:
                legMod(mod);
                break;
            case TOOL_SWORD:
                swordMod(mod);
                break;
            case ARMOR_BOOTS:
                bootMod(mod);
                break;
            case ARMOR_CHEST:
                chestMod(mod);
                break;
        }
    }

    public void axeMod(MODIFIERS mod){

    }
    public void bowMod(MODIFIERS mod){

    }
    public void clawMod(MODIFIERS mod){

    }
    public void pickMod(MODIFIERS mod){

    }
    public void shearMod(MODIFIERS mod){

    }
    public void spadeMod(MODIFIERS mod){

    }
    public void helmMod(MODIFIERS mod){

    }
    public void legMod(MODIFIERS mod){

    }
    public void swordMod(MODIFIERS mod){

    }
    public void bootMod(MODIFIERS mod){

    }
    public void chestMod(MODIFIERS mod){

    }
}
