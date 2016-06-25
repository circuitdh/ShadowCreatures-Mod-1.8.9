package upcraftlp.shadowcreatures.tile;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltar extends TileEntity {

	public InventoryBasic inventory;
	
	public TileEntityAltar() {
		
		inventory = new InventoryBasic("altarInventory", false, 1);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("contents", 10);
		this.inventory = new InventoryBasic("altarInventory", false, 1);
		
		NBTTagCompound tag = list.getCompoundTagAt(0);
		inventory.setInventorySlotContents(0, ItemStack.loadItemStackFromNBT(tag));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList listItems = new NBTTagList();
		NBTTagCompound slotTag = new NBTTagCompound();
		slotTag.setByte("slot", (byte) 0);
		inventory.getStackInSlot(0).writeToNBT(slotTag);
		listItems.appendTag(slotTag);
		compound.setTag("contents", listItems);
	}
	
}
