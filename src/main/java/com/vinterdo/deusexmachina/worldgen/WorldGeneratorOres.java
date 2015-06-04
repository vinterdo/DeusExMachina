package com.vinterdo.deusexmachina.worldgen;

import java.util.Random;

import com.vinterdo.deusexmachina.init.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorOres implements IWorldGenerator 
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		generateEssenceOre(random, chunkX, chunkZ, world);
	}

	private void generateEssenceOre(Random random, int chunkX, int chunkZ, World world) 
	{
		for(int i = 0; i < 3; i++)
		{
	        int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
	        int firstBlockYCoord = random.nextInt(64);
	        int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
	        int numOfOres = 16 + random.nextInt(16);
	        	
	        boolean generate = (new WorldGenMinable(ModBlocks.essenceOre, 0, numOfOres, Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
	    }
	}
}
