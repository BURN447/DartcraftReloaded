package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.Blocks.ModBlocks;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References.*;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * Created by BURN447 on 3/18/2018.
 */
public class ToolModified implements IToolModifier {

    //Acceptable Tools
    List<Item> validTools = TileEntityInfuser.validToolList;
    List<Item> validModifers = TileEntityInfuser.validModifierList;

    public ToolModified(){

    }

    @Override
    public boolean canApplyModifer(ItemStack stack, MODIFIERS mod) {
        for(Item i : validTools){
            if(stack.getItem() == i){
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack applyModifer(ItemStack stack, MODIFIERS mod) {
        return null;
    }

    @Override
    public MODIFIERS findMod(ItemStack stack) {
        if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == ModItems.nuggetForce)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Item.getItemFromBlock(ModBlocks.forceLog))
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;
        else if(stack.getItem() == Items.SUGAR)
            return MODIFIERS.MOD_SPEED;


        else
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
