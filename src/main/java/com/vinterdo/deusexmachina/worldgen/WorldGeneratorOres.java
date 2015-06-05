package com.vinterdo.deusexmachina.worldgen;

import java.util.Random;

import com.vinterdo.deusexmachina.init.ModBlocks;

import net.minecraft.block.Block;
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
		generateOre(random, chunkX, chunkZ, world, ModBlocks.essenceOre);
		generateOre(random, chunkX, chunkZ, world, ModBlocks.copperOre);
		generateOre(random, chunkX, chunkZ, world, ModBlocks.tinOre);
	}

	private void generateOre(Random random, int chunkX, int chunkZ, World world, Block block) 
	{
		for(int i = 0; i < 6; i++)
		{
	        int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
	        int firstBlockYCoord = random.nextInt(64);
	        int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
	        int numOfOres = 2 + random.nextInt(4);
	        	
	        boolean generate = (new WorldGenMinable(block, 0, numOfOres, Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
	    }
	}
	
	
}
