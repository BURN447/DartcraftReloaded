package burn447.dartcraftReloaded.util.Tools;

import burn447.dartcraftReloaded.Blocks.ModBlocks;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.util.References.*;
import burn447.dartcraftReloaded.util.capablilities.IToolModifier;
import net.minecraft.init.Blocks;
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
    public boolean canApplyModifer(MODIFIERS mod) {
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
            return MODIFIERS.MOD_FORCE;
        else if(stack.getItem() == Item.getItemFromBlock(ModBlocks.forceLog))
            return MODIFIERS.MOD_LUMBERJACK;
        else if(stack.getItem() == ModItems.goldenPowerSource)
            return MODIFIERS.MOD_HEAT;
        else if(stack.getItem() == Items.DYE)
            return MODIFIERS.MOD_RAINBOW;
        else if(stack.getItem() == Items.FLINT)
            return MODIFIERS.MOD_GRINDING;
        else if(stack.getItem() == ModItems.cookieFortune)
            return MODIFIERS.MOD_LUCK;
        else if(stack.getItem() == Items.EXPERIENCE_BOTTLE)
            return MODIFIERS.MOD_EXP;
        else if(stack.getItem() == Item.getItemFromBlock(Blocks.CRAFTING_TABLE))
            return MODIFIERS.MOD_CRAFT;
        else if(stack.getItem() == Items.SPIDER_EYE)
            return MODIFIERS.MOD_BANE;
        else if(stack.getItem() == Items.ARROW)
            return MODIFIERS.MOD_BLEED;
        else if(stack.getItem() == Items.GHAST_TEAR)
            return MODIFIERS.MOD_HEALING;
        else if(stack.getItem() == Item.getItemFromBlock(Blocks.WEB))
            return MODIFIERS.MOD_TOUCH;
        else if(stack.getItem() == ModItems.soulWafer)
            return MODIFIERS.MOD_SOUL;
        else if(stack.getItem() == Items.FEATHER)
            return MODIFIERS.MOD_WING;
        else if(stack.getItem() == Items.ENDER_EYE)
            return MODIFIERS.MOD_ENDER;
        else if(stack.getItem() == Items.GLOWSTONE_DUST)
            return MODIFIERS.MOD_LIGHT;
        else if(stack.getItem() == Item.getItemFromBlock(Blocks.BRICK_BLOCK))
            return MODIFIERS.MOD_STURDY;
        else if(stack.getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN))
            return MODIFIERS.MOD_STURDY;
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
