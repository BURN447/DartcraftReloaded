package burn447.dartcraftReloaded.util.capablilities;

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
        System.out.println("Tool Factory Initialization");
        return new IToolModifier() {
            @Override
            public float getDestroySpeed(ItemStack stack, IBlockState state) {
                return 0;
            }

            @Override
            public int getItemEnchantibility() {
                return 0;
            }

            @Override
            public Set<Block> getEffectiveBlocks() {
                return null;
            }

            @Override
            public float getAttackDamage() {
                return 0;
            }

            @Override
            public float getAttackSpeed() {
                return 0;
            }

            @Override
            public Item.ToolMaterial getToolMaterial() {
                return null;
            }

            @Override
            public float getEfficiency() {
                return 0;
            }

            @Override
            public void setEfficiency(float newEfficiency) {

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
