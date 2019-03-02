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

            /**
             * Modifier: Speed
             * Item: Sugar
             * Levels: 5
             * Effect: Gives Player Haste [Level] when holding the tool
             */

            private int speed;

            @Override
            public int getSpeedLevel() {
                return speed;
            }

            @Override
            public boolean hasSpeed() {
                return speed != 0;
            }

            @Override
            public void incrementSpeed() {
                speed++;
            }

            @Override
            public void setSpeed(int newSpeed) {
                speed = newSpeed;
            }

            /**
             * Modifier Heat
             * Item: Golden Power Source
             * Levels: 1
             * Effect: Auto-Smelt Item drops
             */

            private boolean heat;

            @Override
            public boolean hasHeat() {
                return heat;
            }

            @Override
            public void setHeat(boolean val) {
                heat = val;
            }

            /**
             * Modifier: Force
             * Item: Force Nugget
             * Levels: 3
             * Effect: Gives the Sword Knockback
             */

            private int force;

            @Override
            public int getForceLevel() {
                return force;
            }

            @Override
            public boolean hasForce() {
                return force != 0;
            }

            @Override
            public void incrementForce() {
                force++;
            }

            @Override
            public void setForce(int newForce) {
                force = newForce;
            }

            /**
             * Modifier Silk
             * Item: Web
             * Levels: 1
             * Effect: Give Pick/Shovel/Axe Silk Touch
             */

            private boolean silk;

            @Override
            public boolean hasSilk() {
                return silk;
            }

            @Override
            public void setSilk(boolean val) {
                silk = val;
            }

            /**
             * Modifier: Sharpness
             * Item: Claw
             * Levels: 10
             * Effect: Adds Sharpness to Force Sword
             */

            private int sharpness;

            @Override
            public int getSharpLevel() {
                return sharpness;
            }

            @Override
            public boolean hasSharp() {
                return sharpness > 0;
            }

            @Override
            public void incrementSharp() {
                sharpness++;
            }

            @Override
            public void setSharp(int newSharp) {
                sharpness = newSharp;
            }

        };
    }
}
