package burn447.dartcraftReloaded.proxy;

import burn447.dartcraftReloaded.Handlers.DCRCapabilityHandler;
import burn447.dartcraftReloaded.Handlers.DCROreDictionaryHandler;
import burn447.dartcraftReloaded.Items.ModItems;
import burn447.dartcraftReloaded.advancements.ModTriggers;
import burn447.dartcraftReloaded.blocks.ModBlocks;
import burn447.dartcraftReloaded.tileEntity.TileEntityForceFurnace;
import burn447.dartcraftReloaded.tileEntity.TileEntityInfuser;
import burn447.dartcraftReloaded.tileEntity.TileEntityTimeTorch;
import burn447.dartcraftReloaded.util.References;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by BURN447 on 2/4/2018.
 */
public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityInfuser.class, References.modId + ":blockInfuser");
        GameRegistry.registerTileEntity(TileEntityForceFurnace.class, References.modId + ":blockFurnace");
        GameRegistry.registerTileEntity(TileEntityTimeTorch.class, References.modId + ":torchTime");
    }

    public void registerSmeltingRecipes() {
        GameRegistry.addSmelting(ModBlocks.orePower, new ItemStack(ModItems.gemForceGem, 2), 2.0F);
        GameRegistry.addSmelting(ModBlocks.forceLog, new ItemStack(ModItems.goldenPowerSource), 2.0F);
    }
}