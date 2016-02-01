package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonEnding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BShieldPylonEnding extends BTileEntityDEM
{
	
	public BShieldPylonEnding()
	{
		super();
		this.setBlockName("Portable Shield");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		((TEShieldPylonEnding) world.getTileEntity(x, y, z)).hit(player.getLookVec());
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{ 
		if(!world.isRemote) //avoid running the method twice - on server and locally
			{
				if(world.getBlockMetadata(x, y, z) == 0) //shield is off
				{	
					if(side==0)
					{
							world.setBlockMetadataWithNotify(x, y, z, side+1, 3);
							for(int i=0; i<5; i++)
							{
							for (int j=0; j<5; j++)
								{
									if(world.getBlock(x+i-2, y+1, z+j-2).isAir(world, x+1, y+j-2, z+i-2)) 
										world.setBlock(x+i-2,  y+1, z+j-2, ModBlocks.shield);
								
								}
						
							}
					}	
					if(side==1)
					{
							world.setBlockMetadataWithNotify(x, y, z, side+1, 3);
							for(int i=0; i<5; i++)
							{
							for (int j=0; j<5; j++)
								{
									if(world.getBlock(x+i-2, y-1, z+j-2).isAir(world, x+1, y+j-2, z+i-2)) 
										world.setBlock(x+i-2,  y-1, z+j-2, ModBlocks.shield);
								
								}
						
							}
					}	
					if(side==2)
					{
							world.setBlockMetadataWithNotify(x, y, z, side+1, 3); //remember the side
							for(int i=0; i<5; i++)
							{
							for (int j=0; j<5; j++)
								{
									if(world.getBlock(x+i-2, y+j-2, z+1).isAir(world, x+1, y+j-2, z+i-2)) 
										world.setBlock(x+i-2,  y+j-2, z+1, ModBlocks.shield);
								
								}
						
							}
					}
					
					if(side==3)
					{
							world.setBlockMetadataWithNotify(x, y, z, side+1, 3);
							for(int i=0; i<5; i++)
							{
							for (int j=0; j<5; j++)
								{
									if(world.getBlock(x+i-2, y+j-2, z-1).isAir(world, x+1, y+j-2, z+i-2)) 
										world.setBlock(x+i-2,  y+j-2, z-1, ModBlocks.shield);
								
								}
						
							}
					}

					
					if(side==4)
						{
							{
								world.setBlockMetadataWithNotify(x, y, z, side+1, 3);
								for(int i=0; i<5; i++)
								{
								for (int j=0; j<5; j++)
									{
										if(world.getBlock(x+1, y+j-2, z+i-2).isAir(world, x+1, y+j-2, z+i-2)) 
											world.setBlock(x+1,  y+j-2, z+i-2, ModBlocks.shield);
									
									}
							
								}
							}
						}
					
					if(side==5)
					{
							world.setBlockMetadataWithNotify(x, y, z, side+1, 3);
							for(int i=0; i<5; i++)
							{
							for (int j=0; j<5; j++)
								{
									if(world.getBlock(x-1, y+j-2, z+i-2).isAir(world, x+1, y+j-2, z+i-2)) 
										world.setBlock(x-1,  y+j-2, z+i-2, ModBlocks.shield);
								
								}
						
							}
					}
				}
				else //shield was on
				{
					side = world.getBlockMetadata(x, y, z)-1;
					if(side==0)
					{
						world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						for(int i=0; i<5; i++)
						{
						for (int j=0; j<5; j++)
							{
							if(world.getBlock(x+i-2, y+1, z+j-2) == ModBlocks.shield)
								world.setBlock(x+i-2,  y+1, z+j-2, Blocks.air);
							
							}
					
						}
					}
					
					if(side==1)
					{
						world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						for(int i=0; i<5; i++)
						{
						for (int j=0; j<5; j++)
							{
							if(world.getBlock(x+i-2, y-1, z+j-2) == ModBlocks.shield)
								world.setBlock(x+i-2,  y-1, z+j-2, Blocks.air);
							
							}
					
						}
					}
					if(side==2)
					{
						world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						for(int i=0; i<5; i++)
						{
						for (int j=0; j<5; j++)
							{
							if(world.getBlock(x+i-2, y+j-2, z+1) == ModBlocks.shield)
								world.setBlock(x+i-2,  y+j-2, z+1, Blocks.air);
							
							}
					
						}
					}
					
					if(side==3)
					{
						world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						for(int i=0; i<5; i++)
						{
						for (int j=0; j<5; j++)
							{
							if(world.getBlock(x+i-2, y+j-2, z-1) == ModBlocks.shield)
								world.setBlock(x+i-2,  y+j-2, z-1, Blocks.air);
							
							}
					
						}
					}	
					
					if(side==4)
						{
								world.setBlockMetadataWithNotify(x, y, z, 0, 3);
								for(int i=0; i<5; i++)
								{
								for (int j=0; j<5; j++)
									{
									if(world.getBlock(x+1, y+j-2, z+i-2) == ModBlocks.shield)
										world.setBlock(x+1,  y+j-2, z+i-2, Blocks.air);
									
									}
							
								}
						}
					
					if(side==5)
					{
						world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						for(int i=0; i<5; i++)
						{
						for (int j=0; j<5; j++)
							{
							if(world.getBlock(x-1, y+j-2, z+i-2) == ModBlocks.shield)
								world.setBlock(x-1,  y+j-2, z+i-2, Blocks.air);
							
							}
					
						}
					}
					
				}
			}
		return true;
	}
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEShieldPylonEnding();
	}
	
	/*	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}*/
}