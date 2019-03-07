package burn447.dartcraftReloaded.capablilities.ToolModifier;

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

            /**
             * Modifier: Luck
             * Item: Fortune
             * Levels: 5
             * Effect: Adds Fortune to a tool or Looting to a sword
             */

            private int luck;

            @Override
            public int getLuckLevel() {
                return luck;
            }

            @Override
            public boolean hasLuck() {
                return luck > 0;
            }

            @Override
            public void incrementLuck() {
                luck++;
            }

            @Override
            public void setLuck(int newLuck) {
                luck = newLuck;
            }

            /**
             * Modifier: Sturdy
             * Item: Bricks/Obsidian
             * Levels: 10
             * Effect: Adds 1 Level of Unbreaking to tool up to 10
             */

            private int sturdy;

            @Override
            public int getSturdyLevel() {
                return sturdy;
            }

            @Override
            public boolean hasSturdy() {
                return sturdy > 0;
            }

            @Override
            public void incrementSturdy() {
                sturdy++;
            }

            @Override
            public void setSturdy(int newSturdy) {
                sturdy = newSturdy;
            }

            /**
             * Modifier: Rainbow
             * Items: Lapis Lazuli
             * Levels: 1
             * Effect: Makes sheep drop a random amount of colored wool
             */

            boolean rainbow;

            @Override
            public boolean hasRainbow() {
                return rainbow;
            }

            @Override
            public void setRainbow(boolean val) {
                rainbow = val;
            }

            /**
             * Modifier: Lumberjack
             * Items: Force Log
             * Levels: 1
             * Effect: Allows an axe to chop an entire tree down
             */

            boolean lumberJack;

            @Override
            public boolean hasLumberjack() {
                return lumberJack;
            }

            @Override
            public void setLumberjack(boolean val) {
                lumberJack = val;
            }

            /**
             * Modifier: Bleeding
             * Items: Arrow
             * Levels: 2
             * Effect: Applies Bleeding Potion Effect
             */

            int bleed;

            @Override
            public int getBleedLevel() {
                return bleed;
            }

            @Override
            public boolean hasBleed() {
                return bleed > 0;
            }

            @Override
            public void incrementBleed() {
                bleed++;
            }

            @Override
            public void setBleed(int newBleed) {
                bleed = newBleed;
            }

            /**
             * Modifier: Bane
             * Items: Spider Eye
             * Levels: 4
             * Effect: Applies Bane Potion Effect
             */

            int bane;

            @Override
            public int getBaneLevel() {
                return bane;
            }

            @Override
            public boolean hasBane() {
                return bane > 0;
            }

            @Override
            public void incrementBane() {
                bane++;
            }

            @Override
            public void setBane(int newBane) {
                bane = newBane;
            }

            /**
             * Modifier: Wing
             * Items: Feathers
             * Levels: 1
             * Effect: If full armor set is equipped, player can fly
             */

            boolean wing;

            @Override
            public boolean hasWing() {
                return wing;
            }

            @Override
            public void setWing(boolean val) {
                wing = val;
            }
        };
    }
}
