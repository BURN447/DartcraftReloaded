package burn447.dartcraftReloaded.world;

import burn447.dartcraftReloaded.blocks.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by BURN447 on 3/30/2018.
 */
public class DCRWorldGen implements IWorldGenerator {

    //World Generator
    private WorldGenerator forceTree;
    private WorldGenerator forceOre;

    public DCRWorldGen(){
        forceTree = new WorldGenTrees(false, 4, ModBlocks.forceLog.getDefaultState(), ModBlocks.forceLeaves.getDefaultState(), false);
        forceOre = new WorldGenMinable(ModBlocks.orePower.getDefaultState(), 6);
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunk_X * 16 + rand.nextInt(8) + 8;
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(8) + 8;
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        switch(world.provider.getDimension()){
            //Overworld
            case 0:
                this.runGenerator(forceTree, world, random, chunkX, chunkZ, 10, 4, 256);
                this.runGenerator(forceOre, world, random, chunkX, chunkZ, 5, 0, 64);
        }
    }
}