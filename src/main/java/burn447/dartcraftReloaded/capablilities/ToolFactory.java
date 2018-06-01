package burn447.dartcraftReloaded.capablilities;

import burn447.dartcraftReloaded.Items.Tools.ItemForceSword;
import burn447.dartcraftReloaded.dartcraftReloaded;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
                if(stack.getItem() instanceof ItemForceSword){
                    Block block = state.getBlock();

                    if (block == Blocks.WEB)
                    {
                        return 15.0F;
                    }
                    else
                    {
                        Material material = state.getMaterial();
                        return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
                    }
                }

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
