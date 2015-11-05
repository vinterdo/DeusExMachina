Jak dodawać multiblocki z wasnym rendererem? 


Multiblock sklada sie z jednego bloku "mastera" i dowolnej ilosci "slave" bloków. Każdy slave trzyma referencje na swojego mastera i vice versa. 

1. Blok dla mastera
Przede wszystkim bedziemy potrzebować bloku dla mastera. Moze wygladac dowolnie, lecz musi spelniac pare warunkow. Na przykladzie:

```java
public class BDeusMaster extends BTileEntityDEM // musi dziedziczyc z BTileEntityDEM
{
	public BDeusMaster()
	{
		super();
		this.setBlockName("deusMaster");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEDeusMaster(); // metoda createNewTileEntity musi zwracac TileEntity dla mastera
	}
	
	// Ta metoda jest dosc wazna - wywolywana jest kiedy gracz kliknie PPM na blok. Jezeli master jest juz zformowany (jest polaczony z slaveami) otwieramy jego GUI. W przeciwnym wypadku probojemy go formowac
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TEDeusMaster te = (TEDeusMaster) world.getTileEntity(x, y, z);
			if (te.isFormed())
				player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.DEUS.ordinal(), world, x, y, z);
			else
				te.tryForming();
		}
		
		return true; // zwrócenie true oznacza że cos zrobilismy
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TEDeusMaster te = (TEDeusMaster) world.getTileEntity(x, y, z);
		te.destroyMultiblock(); // gdy niszczymy mastera musimy zniszczyc caly multiblock - bez mastera nie moze on istniec
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	// te 3 metody mówia o tym ze mamy customowy renderer - zarejestrujemy go pozniej
	
	@Override
	public int getRenderType()
	{
		return -1; 
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
```
2. Blok dla slavea
```java
public class BDeus extends BTileEntityDEM // znowu dziedziczymy z BTileEntityDEM
{
	public BDeus()
	{
		super();
		this.setBlockName("deus");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEDeus(); // zwracamy tile entity dla slavea
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
	// sprawdzamy czy slave należy do jakiegos mastera (isFormed()), jeżeli tak otwieramy GUI tego mastera. Jezeli nie nalezy, nic nie robimy
		if (!world.isRemote)
		{
			TEDeus te = (TEDeus) world.getTileEntity(x, y, z);
			if (te.isFormed())
			{
				player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.DEUS.ordinal(), world, te.getMaster().xCoord,
						te.getMaster().yCoord, te.getMaster().zCoord);
				return true;
			} else
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
	  // Próbujemy dostać mastera multiblocku. Jeżeli on istnieje (nasz slave należal do jakiegos multiblocku) to usuwamy go z multiblockow. To co zrobi multiblock zalezy już od implementacji mastera - może po prostu zniszczyc multiblock, lub normalnie kontynuowac swoja egzystencje. 
		TEDeusMaster te = (TEDeusMaster) ((TEDeus) world.getTileEntity(x, y, z)).getMaster();
		if (te != null)
			te.removeMember((TEDeus) world.getTileEntity(x, y, z));
			
		super.breakBlock(world, x, y, z, par5, par6);
	  
		if (te != null)
			te.tryForming();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
	 // Ważne! Gdy slave jest sformowany nie chcemy go normalnie renderowac - wiec pobieram sobie tileEntity ktore odpowiada za nasz blok (teMe) i sprawdzam czy jest zformowane. Jak jest, nie chce zeby zadna strona tego bloku sie renderowala. 
		TEDeus teMe = (TEDeus) world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX,
				y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ);
				
		return !teMe.isFormed();
	}
	
	@Override // Nie jest przezroczyste
	public boolean isOpaqueCube()
	{
		return false;
	}
}
```

3. TileEntity dla Slavea
Potrzebne jest tileEntity dla slavea. Bedzie bardzo proste - jedyne co robi to udaje ze jest masterem - jeżeli master implementuje interfejs IInventory, to także slave powinien ten interfejs implementowac. Jednak za kazdym razem bedzie to wygladalo podobnie - chcac wyciagnac item z slavea tak naprawde bedziemy chcieli go wyciagnac z mastera. Wiec mozemy uzyc gotowej klasy ktora juz implementuje ten interfejs:

```java
public class TEDeus extends TEIUniversalMultiblock
{

}
```
Nie musimy robić tutaj nic, wszystko co jest potrzebne do dzialania multibloku jest w TEIUniversalMultiblock. TEI to skrót od TileEntityInventory. Oznacza to ze ta klasa odpowiada za multiblocki które maja jakies inventory. Jeżeli chcielibysmy zeby slave mógl przyjmować energie, fluidy i byl inventory uzyjemy klasy TEIERFMultiblock - TileEntityInventoryEnergyReceiverFluid. Wiecej podobnych klas jest w packagu tileentity.base. 

4. TileEntity dla Mastera

Tu sie dzieje cala magia - ta klasa odpowiada za cala logike tego co robi maszyna. Znowu na przykadzie:

```java
// dziedziczymy z TEIMultiblockMaster - bo robimy mastera i chcemy miec ekwipunek. Mozna po prostu uzyc TEMultiblockMaster i zaimplementowac interfejs IInventory. Obok tego mamy interfejsy od Fluidow (master potrafi przyjmowac ciecze) i Energy receiver (potrafi przyjmowac energie RF)
public class TEDeusMaster extends TEIMultiblockMaster implements IFluidHandler, IEnergyReceiver
{
	...
	@Synchronized(id = 0) // Anotacja synchronized oznacza ze zmienna bedzie wysylana pakietem do klienta kiedy sie zmieni. Dziala tylko dla publicznych pól typu int. Id to id kanalu na ktorym wysylamy pakiet. 
	@NBTSaved(name = "progress") // Anotacja NBTSaved oznacza ze zmienna zostanie zapisana przy wychodzeniu ze swiata i wczytana przy jego ladowaniu. Name to nazwa pod jaka zostanie zapisana wartosc w NBTTagu
	public int								progress;
	@Synchronized(id = 1)
	@NBTSaved(name = "progressTarget")
	public int								progressTarget;

	...
	public static final MultiBlockStructure	structure			= new StructureDeus(); // Struktura multibloku. Ta klasa definiuje jak ma wygladac multiblok zeby byl prawidlowy. Wiecej napisze pozniej. 
	...
																
	public TEDeusMaster()
	{
		super();
	  ...
		setNumOfStacks(3);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		...
		// Tu sie dzieje cala logika maszyny  
	}
	
	@Override // Próba zformowania multiblocku. Jeżeli multiblock jest już zformowany, wywalamy starych slavów i próbujemy formować jeszcze raz.
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		structure.getMembers(this, members);
		super.tryForming();
	}
	
	@Override 
	public boolean isProperMultiblock() // Sprawdzenie czy multiblock jest prawidlowy
	{
		return structure.isValidMultiblock(this);
	}
	
	@Override
	public String getInventoryName() // nazwa ekwipunku
	{
		return ModBlocks.grayMatterCrafterMaster.getUnlocalizedName() + ".name";
	}
	
	...
	
	// Tu ida metody zaimplementowane z interfejsow i pare innych nie waznych dla multiblocku
	
	... 
	
	// Wczytywanie i zapisywanie danych do NBT
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
		energy.readFromNBT(tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tank.writeToNBT(tag);
		energy.writeToNBT(tag);
	}
	
	// Sprawdzenie czy gracz moze uzywac multiblocku. Trzeba pamietac ze gracz klikajac na jakakolwiek czesc multibloku tak naprawde uzywa Mastera.
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 128.0D;
				
	}
	...
	}
}

`````

5. Struktura multiblocku

Klasa potrzebuje 2 metod. Odpowiada za to żeby odpowiednio dodawane byly bloki do mastera oraz za sprawdzanie czy multiblock jest prawidlowy. Klasa jest w sumie opcjonalna - mozna to samo zrobic zwyczajnie nadpisujac metody z TileEntityMultiblockMaster, ale ladniej jest jak uzyje sie tego:

```java
package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.TEDeus;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import net.minecraft.tileentity.TileEntity;

public class StructureDeus extends MultiBlockStructure
{
	
	@Override
	public boolean isValidMultiblock(TEMultiblockMaster temm)
	{
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 7; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = temm.getWorldObj().getTileEntity(temm.xCoord + x - 2, temm.yCoord + y - 6,
							temm.zCoord + z - 2);
					if (te == null)
					{
						return false;
					} else if (te instanceof TEDeus)
					{
						TEMultiblock tem = (TEMultiblock) te;
						if (tem.getMaster() != null)
							return false;
					} else if (te != temm)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void getMembers(TEMultiblockMaster tem, List<TEMultiblock> members)
	{
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 7; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = tem.getWorldObj().getTileEntity(tem.xCoord + x - 2, tem.yCoord + y - 6,
							tem.zCoord + z - 2);
							
					if (te instanceof TEDeus)
					{
						members.add((TEMultiblock) te);
					}
				}
			}
		}
		
	}
	
}
```

6. Container i GUI 

Tu nie bedzie kodu - dziala identycznie jak dla kazdego innego TileEntity.

7. Renderer
Klasa która odpowiada za renderowanie Mastera. Trick polega na tym że gdy multiblock jest niezformowany renderujemy go jak zwykly cube. W momencie gdy blok zostaje zformowany przestajemy rysowac slavy (kod za to odpowiedzialny jest w bloku slavea) i zaczynamy rysowac model multibloku na miejscu wszystkich bloków należacych do multiblocku.

```java

@SideOnly(Side.CLIENT)
public class DeusRenderer extends TileEntitySpecialRenderer
{
	
	// Ladujemy model z pliku .obj (model musi być "triangulated" - inaczej minecraft nie bedzie potrafil go narysowac - mozna to ustawic np. w blenderze)
	private final IModelCustom				model				= AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/deus.obj"));
	private final ModelBlock				modelBlock			= new ModelBlock();
	private final ResourceLocation			textures			= (new ResourceLocation(
			Reference.MOD_ID + ":models/pallete2.png"));
			
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		if (((TEDeusMaster) te).isFormed()) // rysujemy model
		{
			int meta = te.getBlockMetadata();
			
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y - 6, (float) z + 0.5F);
			GL11.glScalef(1 / 18f, 1 / 18f, 1 / 18f);
			if (meta == 1)
				meta = 4;
			if (meta == 0)
				meta = 1;
			GL11.glRotatef(meta * (-90), 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			this.model.renderAll();
			
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			
			renderEnd(x + 0.5D, y - 6D, z + 0.5D);
		} else // rysujemy blok
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			ResourceLocation textures = (new ResourceLocation(Reference.MOD_ID + ":models/BlockDeus.png"));
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			this.modelBlock.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			
			renderEndBlock(x, y, z);
		}
	}
	
	...
	
}
```

8. Wszystko do kupy

Mamy bloki, tileEntity, renderer, container i gui - teraz musimy to porejestrować w klasach:
Bloki w ModBlocks
TileEntity w ModTileEntities
Renderer w ClientProxy
Container i Gui w GuiHandler

Powinno już wszystko dzialac. Zostalo dodac lokalizacje, ikony dla Masterow i receptury craftowania. Done, have fun. 
