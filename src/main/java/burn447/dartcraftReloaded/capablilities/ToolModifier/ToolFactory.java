package burn447.dartcraftReloaded.capablilities.ToolModifier;

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
            boolean repair = false;
            boolean soul = false;
            boolean treasure = false;
            boolean unbreakable = false;
            boolean grinding = false;
            boolean silkTouch = false;
            boolean lumberjack = false;
            boolean ender = false;
            boolean rainbow = false;
            boolean camo = false;
            boolean speed = false;
            boolean damage = false;

            boolean[] luck = {false, false, false, false};
            boolean[] light = {false, false, false, false, false};
            boolean[] sturdy = {false, false, false};
            boolean[] bleeding = {false, false, false, false};

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
            public boolean hasLuckLevel(int level) {
                return luck[level - 1];
            }

            @Override
            public boolean hasAnyLuck() {
                for(boolean bool: luck){
                    if(bool)
                        return true;
                }
                return false;
            }

            @Override
            public void setLuckOne(boolean newVal) {
                luck[0] = newVal;
            }

            @Override
            public void setLuckTwo(boolean newVal) {
                luck[1] = newVal;
            }

            @Override
            public void setLuckThree(boolean newVal) {
                luck[2] = newVal;
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
            public int luckLevel() {
                if(luck[3])
                    return 4;
                else if (luck[2])
                    return 3;
                else if (luck[1])
                    return 2;
                else if (luck[0])
                    return 1;
                else return 0;
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
            public void setBleeding(int level) {
                if(level == 1 && !bleeding[0])
                    bleeding[0] = true;
                else if(level == 2 && bleeding[0] && !bleeding[1])
                    bleeding[1] = true;
                else if(level == 3 && bleeding[0] && bleeding[1] && !bleeding[2])
                    bleeding[2] = true;
                else if(level == 4 && bleeding[0] && bleeding[1] && bleeding[2] && !bleeding[3])
                    bleeding[3] = true;
                else if(level == 5 && bleeding[0] && bleeding[1] && bleeding[2] && bleeding[3] && !bleeding[4])
                    bleeding[4] = true;
            }

            @Override
            public void setBleeding(int level, boolean newVal) {
                bleeding[level - 1] = newVal;
            }

            @Override
            public boolean hasCamo() {
                return camo;
            }

            @Override
            public void setCamo(boolean newVal) {
                camo = newVal;
            }

            @Override
            public boolean hasSpeed() {
                return speed;
            }

            @Override
            public void setSpeed(boolean newVal) {
                speed = newVal;
            }

            @Override
            public boolean hasDamage() {
                return damage;
            }

            @Override
            public void setDamage(boolean newVal) {
                damage = newVal;
            }

            @Override
            public boolean hasLightLevel(int level) {
                return light[level - 1];
            }

            @Override
            public boolean hasAnyLight() {
                for(boolean bool: bleeding){
                    if(bool)
                        return true;
                }
                return false;
            }

            @Override
            public void setLightOne(boolean newVal) {
                light[0] = newVal;
            }

            @Override
            public void setLightTwo(boolean newVal) {
                light[1] = newVal;
            }

            @Override
            public void setLightThree(boolean newVal) {
                light[2] = newVal;
            }

            @Override
            public void setLightFour(boolean newVal) {
                light[3] = newVal;
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
            public boolean hasSturdyLevel(int level) {
                return sturdy[level - 1];
            }

            @Override
            public boolean hasAnySturdy() {
                for(boolean bool: sturdy){
                    if(bool)
                        return true;
                }
                return false;
            }

            @Override
            public void setSturdy(int level, boolean newVal) {
                sturdy[level - 1] = newVal;
            }

            @Override
            public void setSturdy(int level) {
                if(level == 1 && !sturdy[1] && !sturdy[2])
                    sturdy[0] = true;
                else if(level == 2 && sturdy[0] && !sturdy[2])
                    sturdy[1] = true;
                else if(level == 3 && sturdy[0] && sturdy[1])
                    sturdy[2] = true;
            }

            @Override
            public boolean hasRainbow() {
                return rainbow;
            }

            @Override
            public void setRainbow(boolean newVal) {
                rainbow = newVal;
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
            public boolean hasLumberJack() {
                return lumberjack;
            }

            @Override
            public void setLumberJack(boolean newVal) {
                lumberjack = newVal;
            }

            @Override
            public boolean hasEnder() {
                return ender;
            }

            @Override
            public void setEnder(boolean newVal) {
                ender = newVal;
            }

            @Override
            public boolean hasBleeding(int level) {
                return bleeding[level - 1];
            }

            @Override
            public boolean hasAnyBleeding() {
                for(boolean bool: bleeding){
                    if(bool)
                        return true;
                }
                return false;
            }

            @Override
            public boolean hasUpgrades(ItemStack stack) {
                if(!(efficiency == 8.0F))
                    return true;
                else if(!(attackDamage != tm.getAttackDamage()))
                    return true;
                else if(!(force == .5F))
                    return true;
                else if(!freezing || !heat || !wing || !bane || !repair || !soul || !treasure || !unbreakable || !grinding || !silkTouch || !lumberjack || !hasSturdyLevel(1) || !hasLightLevel(1) || !hasLuckLevel(1))
                    return false;
                else
                    return true;
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
        };
    }
}
