package burn447.dartcraftReloaded.tileEntity;

import burn447.dartcraftReloaded.Blocks.ModBlocks;
import burn447.dartcraftReloaded.Energy.DCREnergyStorage;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.Items.Tools.ItemForcePickaxe;
import burn447.dartcraftReloaded.Items.Tools.ItemToolBase;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by BURN447 on 3/30/2018.
 */
public class TileEntityInfuser extends TileEntity implements ITickable, ICapabilityProvider {


    private ItemStackHandler handler;
    private int force;
    private DCREnergyStorage storage;
    private NonNullList<ItemStack> infuserContents = NonNullList.<ItemStack>withSize(11, ItemStack.EMPTY);
    public static List<Item> validToolList = new ArrayList<Item>();
    public static List<Item> validModifierList = new ArrayList<Item>();


    public TileEntityInfuser() {
        super();
        force = 0;
        populateToolList();
        populateModiferList();
        this.handler = new ItemStackHandler(11){
            @Override
            protected int getStackLimit(int slot, ItemStack stack) {
                return 1;
            }
        };

        this.storage = new DCREnergyStorage(500000, 512, 512);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //Items
        handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
        ItemStackHelper.loadAllItems(nbt, this.infuserContents);
        this.infuserContents = NonNullList.<ItemStack>withSize(11, ItemStack.EMPTY);
        //Energy
        storage.readFromNBT(nbt);

        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        //Items
        nbt.setTag("ItemStackHandler", handler.serializeNBT());
        ItemStackHelper.saveAllItems(nbt, this.infuserContents);
        //Energy
        storage.writeToNBT(nbt);
        super.markDirty();
        return super.writeToNBT(nbt);
    }

    @Override
    public void update() {

    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing){
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T)this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing){
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    private boolean hasValidTool(){
        if(handler.getStackInSlot(10) != null){
            for(int i = 0; i < References.numTools; i++){
                if(handler.getStackInSlot(10).getItem() == validToolList.get(i)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasValidModifer(){
        for(int i = 2; i < 10; i++) {
            if(handler.getStackInSlot(i) != null){
                for(int j = 0; j < References.numModifiers - 1; j++){
                    if(handler.getStackInSlot(i).getItem() == validModifierList.get(j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void populateToolList(){
        validToolList.add(ModItems.forcePickaxe);
    }

    private void populateModiferList(){
        validModifierList.add(ModItems.nuggetForce);
        validModifierList.add(Items.SUGAR);
        validModifierList.add(ModItems.goldenPowerSource);
        validModifierList.add(ModItems.cookieFortune);
        validModifierList.add(Items.FLINT);
        validModifierList.add(Items.DYE); //Needs to specify Lapis
        validModifierList.add(Items.EXPERIENCE_BOTTLE);
        validModifierList.add(Items.SPIDER_EYE);
        validModifierList.add(Items.ARROW);
        validModifierList.add(Items.GHAST_TEAR);
        validModifierList.add(ModItems.soulWafer);
        validModifierList.add(Items.FEATHER);
        validModifierList.add(Items.ENDER_PEARL);
        validModifierList.add(Items.GLOWSTONE_DUST);
        validModifierList.add(Item.getItemFromBlock(Blocks.CRAFTING_TABLE));
        validModifierList.add(Item.getItemFromBlock(ModBlocks.forceLog));
        validModifierList.add(Item.getItemFromBlock(Blocks.WEB));
        validModifierList.add(Item.getItemFromBlock(Blocks.OBSIDIAN));
        validModifierList.add(Item.getItemFromBlock(Blocks.BRICK_BLOCK));
    }

}
