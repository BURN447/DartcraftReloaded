package burn447.dartcraftReloaded.tileEntity;

import burn447.dartcraftReloaded.Blocks.ModBlocks;
import burn447.dartcraftReloaded.Energy.DCREnergyStorage;
import burn447.dartcraftReloaded.Fluids.FluidForce;
import burn447.dartcraftReloaded.Items.ItemArmor;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.Items.Tools.*;
import burn447.dartcraftReloaded.util.DartUtils;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_FORCEROD;
import static burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler.CAPABILITY_TOOLMOD;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;


/**
 * Created by BURN447 on 3/30/2018.
 */
public class TileEntityInfuser extends TileEntity implements ITickable, ICapabilityProvider, ITileEntityProvider, IFluidHandler {


    public final ItemStackHandler handler;
    public final ItemStackHandler bookSlotHandler;
    public final ItemStackHandler forceSlotHandler;
    public FluidTank tank;
    public DCREnergyStorage battery;

    private NonNullList<ItemStack> infuserContents = NonNullList.create();

    private static List<Item> validToolList = new ArrayList<>();
    private static List<Item> validModifierList = new ArrayList<>();
    public boolean canWork = false;

    public int processTime = 0;
    public int maxProcessTime = 17;

    public int fluidContained;


    public TileEntityInfuser() {
        populateToolList();
        populateModiferList();
        this.handler = new ItemStackHandler(11) {
            @Override
            protected int getStackLimit(int slot, ItemStack stack) {
                return 1;
            }
        };
        this.bookSlotHandler = new ItemStackHandler(1){
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };

        this.forceSlotHandler = new ItemStackHandler(1);

        this.battery = new DCREnergyStorage(500000, 512, 512);

        tank = new FluidTank(50000);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //Items
        handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
        bookSlotHandler.deserializeNBT(nbt.getCompoundTag("BookSlotHandler"));
        forceSlotHandler.deserializeNBT(nbt.getCompoundTag("ForceSlotHandler"));
        ItemStackHelper.loadAllItems(nbt, this.infuserContents);
        //Energy
        //battery.readFromNBT(nbt);
        //Fluids
        tank.readFromNBT(nbt);

        super.markDirty();
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        //Items
        nbt.setTag("ItemStackHandler", handler.serializeNBT());
        nbt.setTag("ForceSlotHandler", forceSlotHandler.serializeNBT());
        nbt.setTag("BookSlotHandler", bookSlotHandler.serializeNBT());
        ItemStackHelper.saveAllItems(nbt, this.infuserContents);
        //Energy
        //battery.writeToNBT(nbt);
        //Fluids
        tank.writeToNBT(nbt);

        return super.writeToNBT(nbt);
    }

    @Override
    public void update() {
        fluidContained = tank.getFluidAmount();
        if(world != null) {
            if (!world.isRemote) {
                if (forceSlotHandler.getStackInSlot(0).getItem() == ModItems.gemForceGem) {
                    FluidStack force = new FluidStack(FluidRegistry.getFluid("force"), 500);

                    if (tank.getFluidAmount() < tank.getCapacity() - 100) {
                        fill(force, true);
                        if (forceSlotHandler.getStackInSlot(0).getCount() > 1) {
                            forceSlotHandler.getStackInSlot(0).setCount(forceSlotHandler.getStackInSlot(0).getCount() - 1);
                        } else
                            forceSlotHandler.setStackInSlot(0, ItemStack.EMPTY);

                        markDirty();
                        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                    }
                }
                if (canWork) {
                    if (processTime == maxProcessTime) {
                        this.markDirty();
                        if (hasValidTool()) {
                            for (int i = 0; i < 8; i++) {
                                if (hasValidModifer(i)) {
                                    ItemStack mod = getModifer(i);
                                    ItemStack stack = handler.getStackInSlot(8);
                                    boolean success = applyModifier(stack, mod);
                                    if (success) {
                                        handler.setStackInSlot(i, ItemStack.EMPTY);
                                        tank.drain(1000, true);
                                        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                                    }
                                }
                            }
                        }
                        canWork = false;
                        processTime = 0;
                    }
                    processTime++;
                }
            }
        }
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        markDirty();
        writeToNBT(nbt);
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
        markDirty();
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
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) this.handler;
        if(capability == FLUID_HANDLER_CAPABILITY)
            return (T) this.tank;
        if(capability == CapabilityEnergy.ENERGY)
            return (T) this.battery;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == FLUID_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY)
            return true;
        return super.hasCapability(capability, facing);
    }

    private boolean hasValidTool() {
        if (!handler.getStackInSlot(8).isEmpty()) {
            for (int i = 0; i < References.numTools; i++) {
                if (handler.getStackInSlot(8).getItem() == validToolList.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasValidModifer(int slot) {
        if (!handler.getStackInSlot(8).isEmpty()) {
            for (int j = 0; j < References.numModifiers - 1; j++) {
                if (handler.getStackInSlot(slot).getItem() == validModifierList.get(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void populateToolList() {
        validToolList.add(ModItems.forcePickaxe);
        validToolList.add(ModItems.forceAxe);
        validToolList.add(ModItems.forceShovel);
        validToolList.add(ModItems.forceSword);
        validToolList.add(ModItems.forceRod);
        validToolList.add(ModItems.forceShears);
        validToolList.add(ModItems.forceHelmet);
        validToolList.add(ModItems.forceChest);
        validToolList.add(ModItems.forceLegs);
        validToolList.add(ModItems.forceBoots);
    }

    private void populateModiferList() {
        validModifierList.add(ModItems.nuggetForce);
        validModifierList.add(ModItems.claw);
        validModifierList.add(ModItems.fortune);
        validModifierList.add(Items.SUGAR);
        validModifierList.add(Items.COAL);
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
        validModifierList.add(Items.POTIONITEM);
        validModifierList.add(Item.getItemFromBlock(Blocks.CRAFTING_TABLE));
        validModifierList.add(Item.getItemFromBlock(ModBlocks.forceLog));
        validModifierList.add(Item.getItemFromBlock(Blocks.WEB));
        validModifierList.add(Item.getItemFromBlock(Blocks.OBSIDIAN));
        validModifierList.add(Item.getItemFromBlock(Blocks.BRICK_BLOCK));
    }

    private ItemStack getModifer(int slot) {
        if (!handler.getStackInSlot(8).isEmpty()) {
            for (int j = 0; j < References.numModifiers - 1; j++) {
                if (handler.getStackInSlot(slot).getItem() == validModifierList.get(j)) {
                    return handler.getStackInSlot(slot);
                }
                }
            }
        return null;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInfuser();
    }

    private boolean applyModifier(ItemStack stack, ItemStack mod) {
        Item modifier = mod.getItem();
        if (modifier == Items.SUGAR)
            return addSpeedModifier(stack);
        if (modifier == Items.COAL)
            return addHeatModifier(stack);
        if (modifier == Items.FLINT)
            return addGrindingModifier(stack);
        if (modifier == ModItems.nuggetForce)
            return addForceModifier(stack);
        if (modifier == Item.getItemFromBlock(Blocks.WEB))
            return addSilkTouchModifier(stack);
        if (modifier == ModItems.claw)
            return addDamageModifier(stack);
        if (modifier == ModItems.fortune)
            return addLuckModifier(stack);
        if (modifier == Items.GLOWSTONE_DUST)
            return addLightModifier(stack);
        if (modifier == Item.getItemFromBlock(Blocks.BRICK_BLOCK) || modifier == Item.getItemFromBlock(Blocks.OBSIDIAN))
            return addSturdyModifier(stack);
        if (modifier == Item.getItemFromBlock(ModBlocks.forceLog))
            return addLumberjackModifier(stack);
        if (modifier == Items.GHAST_TEAR)
            return addHealingModifier(stack);
        if (modifier == Items.ENDER_PEARL)
            return addEnderModifier(stack);
        if(modifier == Items.ARROW)
            return addBleedingModifier(stack);
        if(modifier == Items.SPIDER_EYE)
            return addBaneModifier(stack);
        if (modifier == Items.POTIONITEM){
            List<PotionEffect> effects = PotionUtils.getEffectsFromStack(mod);
                for(PotionEffect e : effects){
                    if(e.getPotion() == MobEffects.NIGHT_VISION){
                        return addSightModifier(stack);
                    }
                    if(e.getPotion() == MobEffects.INVISIBILITY){
                        return addCamoModifier(stack);
                    }
            }
        }
        if(modifier == Items.DYE){
            if(mod.getMetadata() == 4){
                return addRainbowModifier(stack);
            }
        }

        return false;
    }

    private boolean addSpeedModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemToolBase) {
            if (stack.getItem() instanceof ItemForcePickaxe || stack.getItem() instanceof ItemForceShovel || stack.getItem() instanceof ItemForceAxe) {
                if (stack.hasCapability(CAPABILITY_TOOLMOD, null)) {
                    if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 8.0) {
                        stack.getCapability(CAPABILITY_TOOLMOD, null).setEfficiency(12.0F);
                        return true;
                    } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getEfficiency() == 12.0) {
                        stack.getCapability(CAPABILITY_TOOLMOD, null).setEfficiency(16.0F);
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private boolean addHeatModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemToolBase) {
            if (stack.hasCapability(CAPABILITY_TOOLMOD, null)) {
                stack.getCapability(CAPABILITY_TOOLMOD, null).setHeat(true);
                return true;
            }
        }
        return false;
    }

    private boolean addForceModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemForceSword) {
            if (stack.hasCapability(CAPABILITY_TOOLMOD, null)) {
                if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 1.5F)
                    stack.getCapability(CAPABILITY_TOOLMOD, null).setKnockback(1.5F);
                else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getKnockback() == 1.5F)
                    stack.getCapability(CAPABILITY_TOOLMOD, null).setKnockback(2.0F);
                return true;
            }
        }
        return false;
    }

    private boolean addGrindingModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemForceAxe || stack.getItem() instanceof ItemForceShovel || stack.getItem() instanceof ItemForcePickaxe) {
            if (stack.hasCapability(CAPABILITY_TOOLMOD, null)) {
                stack.getCapability(CAPABILITY_TOOLMOD, null).setGrinding(true);
                return true;
            }
        }
        return false;
    }

    private boolean addSilkTouchModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemForceAxe || stack.getItem() instanceof ItemForceShovel || stack.getItem() instanceof ItemForcePickaxe) {
            stack.addEnchantment(Enchantments.SILK_TOUCH, 1);
        }
        return false;
    }

    private boolean addDamageModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemForceSword) {
            if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 8.0F) {
                stack.getCapability(CAPABILITY_TOOLMOD, null).setAttackDamage(12.0F);
                return true;
            } else if (stack.getCapability(CAPABILITY_TOOLMOD, null).getAttackDamage() == 12.0F) {
                stack.getCapability(CAPABILITY_TOOLMOD, null).setAttackDamage(16.0F);
                return true;
            }
        }
        return false;
    }

    private boolean addLuckModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemForcePickaxe || stack.getItem() instanceof ItemForceShovel || stack.getItem() instanceof ItemForceAxe) {
            if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(1) && !stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(4)) {
                stack.addEnchantment(Enchantments.FORTUNE, 1);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(1);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(2)) {
                DartUtils.removeEnchant(Enchantments.FORTUNE, stack);
                stack.addEnchantment(Enchantments.FORTUNE, 2);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(2);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(3)) {
                DartUtils.removeEnchant(Enchantments.FORTUNE, stack);
                stack.addEnchantment(Enchantments.FORTUNE, 3);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(3);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(4)) {
                DartUtils.removeEnchant(Enchantments.FORTUNE, stack);
                stack.addEnchantment(Enchantments.FORTUNE, 4);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(4);
                return true;
            }
        } else if (stack.getItem() instanceof ItemForceSword) {
            if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(1) && !stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(4)) {
                stack.addEnchantment(Enchantments.LOOTING, 1);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(1);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(2)) {
                DartUtils.removeEnchant(Enchantments.LOOTING, stack);
                stack.addEnchantment(Enchantments.LOOTING, 2);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(2);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(3)) {
                DartUtils.removeEnchant(Enchantments.LOOTING, stack);
                stack.addEnchantment(Enchantments.LOOTING, 3);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(3);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLuckLevel(4)) {
                DartUtils.removeEnchant(Enchantments.LOOTING, stack);
                stack.addEnchantment(Enchantments.LOOTING, 4);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLuck(4);
                return true;
            }
        }
        return false;
    }

    private boolean addLightModifier(ItemStack stack) {
        //Smite
        if (stack.getItem() instanceof ItemForceSword) {
            if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLightLevel(1)) {
                stack.addEnchantment(Enchantments.SMITE, 1);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLight(1);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLightLevel(2)) {
                DartUtils.removeEnchant(Enchantments.SMITE, stack);
                stack.addEnchantment(Enchantments.SMITE, 2);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLight(2);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLightLevel(3)) {
                DartUtils.removeEnchant(Enchantments.SMITE, stack);
                stack.addEnchantment(Enchantments.SMITE, 3);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLight(3);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLightLevel(4)) {
                DartUtils.removeEnchant(Enchantments.SMITE, stack);
                stack.addEnchantment(Enchantments.SMITE, 4);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLight(4);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLightLevel(5)) {
                DartUtils.removeEnchant(Enchantments.SMITE, stack);
                stack.addEnchantment(Enchantments.SMITE, 5);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setLight(5);
                return true;
            }
        }
        return false;
    }

    private boolean addSturdyModifier(ItemStack stack) {
        if (stack.getItem() instanceof ItemToolBase) {
            if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasSturdyLevel(1)) {
                stack.addEnchantment(Enchantments.UNBREAKING, 1);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setSturdy(1);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasSturdyLevel(2)) {
                DartUtils.removeEnchant(Enchantments.UNBREAKING, stack);
                stack.addEnchantment(Enchantments.UNBREAKING, 2);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setSturdy(2);
                return true;
            } else if (!stack.getCapability(CAPABILITY_TOOLMOD, null).hasSturdyLevel(3)) {
                DartUtils.removeEnchant(Enchantments.UNBREAKING, stack);
                stack.addEnchantment(Enchantments.UNBREAKING, 3);
                stack.getCapability(CAPABILITY_TOOLMOD, null).setSturdy(3);
                return true;
            }
        }
        return false;
    }

    private boolean addLumberjackModifier(ItemStack stack){
        if(!stack.getCapability(CAPABILITY_TOOLMOD, null).hasLumberJack()){
            stack.getCapability(CAPABILITY_TOOLMOD, null).setLumberJack(true);
            return true;
        }
        else
            return false;
    }

    private boolean addHealingModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceRod){
            if(!stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(1)) {
                stack.getCapability(CAPABILITY_FORCEROD, null).setRodOfHealing(true, 1);
                return true;
            }
            else if(!stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(2) && stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(1)){
                stack.getCapability(CAPABILITY_FORCEROD, null).setRodOfHealing(true, 2);
                return true;
            }
            else if(!stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(3) && stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(2) && stack.getCapability(CAPABILITY_FORCEROD, null).isRodOfHealing(1)){
                stack.getCapability(CAPABILITY_FORCEROD, null).setRodOfHealing(true, 3);
                return true;
            }
        }
        return false;
    }

    private boolean addCamoModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceRod){
            stack.getCapability(CAPABILITY_FORCEROD, null).setCamoModifier(true);
            return true;
        }
        return false;
    }

    private boolean addEnderModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceRod){
            stack.getCapability(CAPABILITY_FORCEROD, null).setEnderModifier(true);
            return true;
        }
        else if(stack.getItem() instanceof ItemForceSword){
            stack.getCapability(CAPABILITY_TOOLMOD, null).setEnder(true);
            return true;
        }
        return false;
    }

    private boolean addSightModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceRod){
            stack.getCapability(CAPABILITY_FORCEROD, null).setSightModifier(true);
            return true;
        }
        return false;
    }

    private boolean addRainbowModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceShears){
            stack.getCapability(CAPABILITY_TOOLMOD, null).setRainbow(true);
            return true;
        }
        return false;
    }

    private boolean addBleedingModifier(ItemStack stack){
        if(stack.getItem() instanceof ItemForceSword){
            if(!stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1)){
                stack.getCapability(CAPABILITY_TOOLMOD, null).setBleeding(1);
                return true;
            }
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1) && !stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2)){
                stack.getCapability(CAPABILITY_TOOLMOD, null).setBleeding(2);
                return true;
            }
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2) && stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1) && !stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(3)){
                stack.getCapability(CAPABILITY_TOOLMOD, null).setBleeding(3);
                return true;
            }
            else if(stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(3) && stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(2) && stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(1) && !stack.getCapability(CAPABILITY_TOOLMOD, null).hasBleeding(4)){
                stack.getCapability(CAPABILITY_TOOLMOD, null).setBleeding(4);
                return true;
            }
        }
        return false;
    }

    private boolean addBaneModifier(ItemStack stack){
        if (stack.getItem() instanceof ItemToolBase || stack.getItem() instanceof ItemArmor) {
            if (stack.hasCapability(CAPABILITY_TOOLMOD, null)) {
                stack.getCapability(CAPABILITY_TOOLMOD, null).setBane(true);
                return true;
            }
        }

        return false;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        FluidStack resourceCopy = resource.copy();

        if (tank.getFluid() != null) {
            if (tank.getFluid().getFluid() instanceof FluidForce || tank.getFluidAmount() == 0) {
                tank.fill(resourceCopy, true);
            }
        }
        if(tank.getFluid() == null){
            tank.fill(resourceCopy, true);
        }
        return resource.amount;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if(!isFluidEqual(resource))
            return null;
        if(!doDrain){
            int amount = tank.getFluidAmount() - resource.amount < 0 ? tank.getFluidAmount() : resource.amount;
            return new FluidStack(tank.getFluid(), amount);
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return this.tank.drain(maxDrain, doDrain);
    }

    public float getFluidPercentage()
    {
        return (float) tank.getFluidAmount() / (float) tank.getCapacity();
    }

    public int getFluidGuiHeight(int maxHeight)
    {
        return (int) Math.ceil(getFluidPercentage() * (float) maxHeight);
    }

    protected boolean isFluidEqual(FluidStack fluid)
    {
        return isFluidEqual(fluid.getFluid());
    }

    protected boolean isFluidEqual(Fluid fluid)
    {
        return tank.getFluid().equals(fluid);
    }

}