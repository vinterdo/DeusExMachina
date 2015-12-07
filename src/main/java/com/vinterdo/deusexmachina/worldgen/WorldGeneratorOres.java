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
	Configuration config = ConfigurationHandler.configuration;
	
	boolean generateCopper = config.get("OreGen" + config.CATEGORY_SPLITTER + "Copper", "generateCopper", true).getBoolean();
	int copperMinHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Copper", "generateCopperHeightMin", 5).getInt();
	int copperMaxHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Copper", "generateCopperHeightMax", 64).getInt();
	int copperVeinsPerChunk = config.get("OreGen" + config.CATEGORY_SPLITTER + "Copper", "generateCopperVeinsPerChunk", 6).getInt();
	int copperBlocksPerVein = config.get("OreGen" + config.CATEGORY_SPLITTER + "Copper", "generateCopperBlocksPerVein", 2).getInt();
	
	boolean generateEssence = config.get("OreGen" + config.CATEGORY_SPLITTER + "Essence", "generateEssence", true).getBoolean();
	int essenceMinHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Essence", "generateEssenceHeightMin", 5).getInt();
	int essenceMaxHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Essence", "generateEssenceHeightMax", 64).getInt();
	int essenceVeinsPerChunk = config.get("OreGen" + config.CATEGORY_SPLITTER + "Essence", "generateEssenceVeinsPerChunk", 6).getInt();
	int essenceBlocksPerVein = config.get("OreGen" + config.CATEGORY_SPLITTER + "Essence", "generateEssenceBlocksPerVein", 2).getInt();
	
	boolean generateTin = config.get("OreGen" + config.CATEGORY_SPLITTER + "Tin", "generateTin", true).getBoolean();
	int tinMinHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Tin", "generateTinHeightMin", 5).getInt();
	int tinMaxHeight = config.get("OreGen" + config.CATEGORY_SPLITTER + "Tin", "generateTinHeightMax", 64).getInt();
	int tinVeinsPerChunk = config.get("OreGen" + config.CATEGORY_SPLITTER + "Tin", "generateTinVeinsPerChunk", 6).getInt();
	int tinBlocksPerVein = config.get("OreGen" + config.CATEGORY_SPLITTER + "Tin", "generateTinBlocksPerVein", 2).getInt();
	
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
