package burn447.dartcraftReloaded.util.capablilities;

import burn447.dartcraftReloaded.dartcraftReloaded;
import burn447.dartcraftReloaded.util.Tools.ToolModified;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Set;
import java.util.concurrent.Callable;

public class ToolFactory implements Callable<IToolModifier> {

    @Override
    public IToolModifier call() throws Exception {
        return new IToolModifier() {
            private Item.ToolMaterial tm = dartcraftReloaded.forceToolMaterial;
            private float efficiency = 8.0F;
            @Override
            public float getDestroySpeed(ItemStack stack, IBlockState state) {
                return efficiency;
            }

            @Override
            public int getItemEnchantibility() {
                return tm.getEnchantability();
            }

            @Override
            public Set<Block> getEffectiveBlocks() {
                return null;
            }

            @Override
            public float getAttackDamage() {
                return tm.getAttackDamage();
            }

            @Override
            public float getAttackSpeed() {
                return 0;
            }

            @Override
            public Item.ToolMaterial getToolMaterial() {
                return tm;
            }

            @Override
            public float getEfficiency() {
                return efficiency;
            }

            @Override
            public void setEfficiency(float newEfficiency) {
                efficiency = newEfficiency;
            }

            @Override
            public NBTTagCompound serializeNBT() {
                return null;
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt) {

            }
        };
    }
}
