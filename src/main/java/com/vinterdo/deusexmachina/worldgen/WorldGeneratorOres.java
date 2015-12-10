package com.vinterdo.deusexmachina.worldgen;

import java.util.Random;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.handler.ConfigurationHandler;
import net.minecraftforge.common.config.Configuration;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorOres implements IWorldGenerator
{
	
	// Get values from config
	
	boolean generateCopper = ConfigurationHandler.generateCopper;
	int copperMinHeight = ConfigurationHandler.generateCopperMinHeight;
	int copperMaxHeight = ConfigurationHandler.generateCopperMaxHeight;
	int copperVeinsPerChunk = ConfigurationHandler.generateCopperVeinsPerChunk;
	int copperBlocksPerVein = ConfigurationHandler.generateCopperBlocksPerVein;
	
	boolean generateEssence = ConfigurationHandler.generateEssence;
	int essenceMinHeight = ConfigurationHandler.generateEssenceMinHeight;
	int essenceMaxHeight = ConfigurationHandler.generateEssenceMaxHeight;
	int essenceVeinsPerChunk = ConfigurationHandler.generateEssenceVeinsPerChunk;
	int essenceBlocksPerVein = ConfigurationHandler.generateEssenceBlocksPerVein;
	
	boolean generateTin = ConfigurationHandler.generateTin;
	int tinMinHeight = ConfigurationHandler.generateTinMinHeight;
	int tinMaxHeight = ConfigurationHandler.generateTinMaxHeight;
	int tinVeinsPerChunk = ConfigurationHandler.generateTinVeinsPerChunk;
	int tinBlocksPerVein = ConfigurationHandler.generateTinBlocksPerVein;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		if (generateEssence) generateOre(random, chunkX, chunkZ, world, ModBlocks.essenceOre, essenceVeinsPerChunk, essenceBlocksPerVein, essenceMinHeight, essenceMaxHeight);
		if (generateCopper) generateOre(random, chunkX, chunkZ, world, ModBlocks.copperOre, copperVeinsPerChunk, copperBlocksPerVein, copperMinHeight, copperMaxHeight);
		if (generateTin) generateOre(random, chunkX, chunkZ, world, ModBlocks.tinOre, tinVeinsPerChunk, tinBlocksPerVein, tinMinHeight, tinMaxHeight);
	}
	
	private void generateOre(Random random, int chunkX, int chunkZ, World world, Block block, int numOfVeins, int numOfOres, int minY, int maxY)
	{
		for (int i = 0; i < numOfVeins; i++)
		{
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = minY + random.nextInt(maxY-minY);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			
			boolean generate = (new WorldGenMinable(block, 0, numOfOres, Blocks.stone)).generate(world, random,
					firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
	
}
