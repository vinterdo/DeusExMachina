package com.vinterdo.deusexmachina.research;

import java.util.HashMap;
import java.util.Map.Entry;

import com.vinterdo.deusexmachina.client.gui.GuiDeus;
import com.vinterdo.deusexmachina.recipes.Recipes;
import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;

public class ResearchTree
{
	ResearchNode							root;
											
	private HashMap<String, ResearchNode>	nodes;
											
	public ResearchTree()
	{
		nodes = new HashMap<String, ResearchNode>();
	}
	
	public void createTree()
	{
		root = new ResearchNode(Recipes.infusedDiamondRecipe, null, 100, 5000, 2000, 100, 100,
				new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"));
				
		ResearchNode node1 = new ResearchNode(Recipes.researchCoreRecipe, root, 100, 5000, 2000, 200, 150,
				new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"));
				
		try
		{
			addNode(root);
			addNode(node1);
		} catch (ResearchNodeNotUniqueException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addNode(ResearchNode node) throws ResearchNodeNotUniqueException
	{
		if (nodes.containsKey(node.name))
			throw new ResearchNodeNotUniqueException(node.name);
		nodes.put(node.name, node);
	}
	
	public void renderTree(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		if (root != null)
			root.render(mousex, mousey, partialTick, offsetx, offsety);
	}
	
	public NBTTagCompound toNBT()
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("node", root.getName());
		NBTTagList list = new NBTTagList();
		
		for (Entry<String, ResearchNode> node : nodes.entrySet())
		{
			list.appendTag(node.getValue().toNBT());
		}
		tag.setTag("tree", list);
		return tag;
	}
	
	public void setRender(GuiDeus gui)
	{
		
		for (Entry<String, ResearchNode> node : nodes.entrySet())
		{
			node.getValue().setRender(gui);
		}
	}
	
	public static ResearchTree fromNBT(NBTTagCompound tag)
	{
		ResearchTree tree = new ResearchTree();
		NBTTagList list = tag.getTagList("tree", Constants.NBT.TAG_COMPOUND);
		
		HashMap<String, String> parentNames = new HashMap<String, String>();
		
		for (int i = 0; i < list.tagCount(); i++)
		{
			ResearchNode node = ResearchNode.fromNBT(list.getCompoundTagAt(i));
			try
			{
				tree.addNode(node);
				parentNames.put(node.getName(), list.getCompoundTagAt(i).getString("parent"));
			} catch (ResearchNodeNotUniqueException e)
			{
				e.printStackTrace();
			}
		}
		
		for (Entry<String, ResearchNode> node : tree.nodes.entrySet())
		{
			node.getValue().setParent(tree.nodes.get(parentNames.get(node.getValue().getName())));
		}
		
		tree.root = tree.nodes.get(tag.getString("node"));
		return tree;
	}
	
	public ResearchNode getRoot()
	{
		return root;
	}
}
