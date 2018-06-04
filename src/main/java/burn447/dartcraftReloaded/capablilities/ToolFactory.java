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
            private float attackDamage = tm.getAttackDamage();
            private float force = 0.5F;

            boolean freezing = false;
            boolean heat = false;
            boolean wing = false;
            boolean bane = false;
            boolean bleeding = false;
            boolean repair = false;
            boolean soul = false;
            boolean treasure = false;
            boolean unbreakable = false;
            boolean grinding = false;
            boolean silkTouch = false;
            boolean sturdy = false;

            boolean[] luck = {false, false, false, false};
            boolean[] light = {false, false, false, false, false};

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
                return attackDamage;
            }

            @Override
            public void setAttackDamage(float newVal){attackDamage = newVal;}

            @Override
            public boolean hasFreezing() {
                return freezing;
            }

            @Override
            public void setFreezing(boolean newVal) {
                freezing = newVal;
            }

            @Override
            public boolean hasHeat() {
                return heat;
            }

            @Override
            public void setHeat(boolean newVal) {
                heat = newVal;
            }

            @Override
            public boolean hasLuckOne() {
                return luck[0];
            }

            @Override
            public void setLuckOne(boolean newVal) {
                luck[0] = newVal;
            }

            @Override
            public boolean hasLuckTwo() {
                return luck[1];
            }

            @Override
            public void setLuckTwo(boolean newVal) {
                luck[1] = newVal;
            }

            @Override
            public boolean hasLuckThree() {
                return luck[2];
            }

            @Override
            public void setLuckThree(boolean newVal) {
                luck[2] = newVal;
            }

            @Override
            public boolean hasLuckFour() {
                return luck[3];
            }

            @Override
            public void setLuckFour(boolean newVal) {
                luck[3] = newVal;
            }

            @Override
            public void setLuck(int level) {
                if(level == 1 && !luck[1] && !luck[2] && !luck[3])
                    luck[0] = true;
                else if(level == 2 && luck[0] && !luck[2] && !luck[3])
                    luck[1] = true;
                else if(level == 3 && luck[0] && luck[1] && !luck[3])
                    luck[2] = true;
                else if(level == 4 && luck[0] && luck[1] && luck[2])
                    luck[3] = true;
            }

            @Override
            public boolean hasWing() {
                return wing;
            }

            @Override
            public void setWing(boolean newVal) {
                wing = newVal;
            }

            @Override
            public boolean hasBane() {
                return bane;
            }

            @Override
            public void setBane(boolean newVal) {
                bane = newVal;
            }

            @Override
            public boolean hasBleeding() {
                return false;
            }

            @Override
            public void setBleeding(boolean newVal) {
                bleeding = newVal;
            }

            @Override
            public boolean hasLightOne() {
                return light[0];
            }

            @Override
            public void setLightOne(boolean newVal) {
                light[0] = newVal;
            }

            @Override
            public boolean hasLightTwo() {
                return light[1];
            }

            @Override
            public void setLightTwo(boolean newVal) {
                light[1] = newVal;
            }

            @Override
            public boolean hasLightThree() {
                return light[2];
            }

            @Override
            public void setLightThree(boolean newVal) {
                light[2] = newVal;
            }

            @Override
            public boolean hasLightFour() {
                return light[3];
            }

            @Override
            public void setLightFour(boolean newVal) {
                light[3] = newVal;
            }

            @Override
            public boolean hasLightFive() {
                return light[4];
            }

            @Override
            public void setLightFive(boolean newVal) {
                light[4] = newVal;
            }

            @Override
            public void setLight(int level) {
                if(level == 1 && !light[1] && !light[2] && !light[3] && !light[4])
                    light[0] = true;
                else if(level == 2 && light[0] && !light[2] && !light[3] && !light[4])
                    light[1] = true;
                else if(level == 3 && light[0] && light[1] && !light[3] && !light[4])
                    light[2] = true;
                else if(level == 4 && light[0] && light[1] && light[2] && !light[4])
                    light[3] = true;
                else if(level == 5 && light[0] && light[1] && light[2] && light[3])
                    light[4] = true;
            }

            @Override
            public boolean hasRepair() {
                return repair;
            }

            @Override
            public void setRepair(boolean newVal) {
                repair = newVal;
            }

            @Override
            public boolean hasSoul() {
                return soul;
            }

            @Override
            public void setSoul(boolean newVal) {
                soul = newVal;
            }

            @Override
            public boolean hasTreasure() {
                return treasure;
            }

            @Override
            public void setTreasure(boolean newVal) {
                treasure = newVal;
            }

            @Override
            public boolean isUnbreakable() {
                return unbreakable;
            }

            @Override
            public void setUnbreaking(boolean newVal) {
                unbreakable = newVal;
            }

            @Override
            public boolean hasGrinding() {
                return grinding;
            }

            @Override
            public void setGrinding(boolean newVal) {
                grinding = newVal;
            }

            @Override
            public boolean hasTouch() {
                return silkTouch;
            }

            @Override
            public void setTouch(boolean newVal) {
                silkTouch = newVal;
            }

            @Override
            public boolean hasSturdy() {
                return sturdy;
            }

            @Override
            public void setSturdy(boolean newVal) {
                sturdy = newVal;
            }

            @Override
            public float getKnockback() {
                return force;
            }

            @Override
            public void setKnockback(float newVal) {
                force = newVal;
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
