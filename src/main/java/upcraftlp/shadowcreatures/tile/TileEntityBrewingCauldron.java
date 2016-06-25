package upcraftlp.shadowcreatures.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import upcraftlp.shadowcreatures.crafting.CauldronCrafting;

public class TileEntityBrewingCauldron extends TileEntity implements IFluidHandler{

	public InventoryBasic inventory;
	public FluidTank tank;
	private NBTTagList list = new NBTTagList();
	
	public TileEntityBrewingCauldron() {
		
		this.inventory = new InventoryBasic("cauldronInventory", false, 1);
		this.tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
		
	}
	
	public void interact(EntityPlayer player)
	{
		this.markDirty();
		if(FluidContainerRegistry.isContainer(player.inventory.getCurrentItem()))
		{
			if(this.worldObj.isRemote) return;
			if(this.tank.getFluid() != null)
			{
				if(FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()).isFluidEqual(this.tank.getFluid()))
				{
					this.fill(EnumFacing.UP, FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()), true);
					//TODO CINTAINER LOGIC PRIORITY = LOW
					player.replaceItemInInventory(player.inventory.currentItem, new ItemStack(Items.bucket, 1));
				}
			}
			else
			{
				if(this.worldObj.isRemote) return;
				this.fill(EnumFacing.UP, FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()), true);
				player.replaceItemInInventory(player.inventory.currentItem, new ItemStack(Items.bucket, 1));
			}
			
			
			
			return;
		}
			
		if(this.inventory.getStackInSlot(0) != null) 
		{
			//remove and drop item
			if(!worldObj.isRemote)
			{
				EntityItem item = new EntityItem(worldObj, player.chasingPosX, player.chasingPosY, player.chasingPosZ, this.inventory.getStackInSlot(0));
				worldObj.spawnEntityInWorld(item);
			}
			inventory.clear();
			return;
		}
		
		//otherwise add item
		if(player.getCurrentEquippedItem() != null)
		{
			inventory.setInventorySlotContents(0, new ItemStack(player.getCurrentEquippedItem().getItem(), 1));
			player.inventory.decrStackSize(player.inventory.currentItem, 1);
		}
		if(this.tank.getFluid() != null) CauldronCrafting.verify(this.inventory, worldObj, player, this.tank);
	}
	
	public void drop(BlockPos pos)
	{
		if(worldObj.isRemote) return;
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack itemStack = inventory.getStackInSlot(i);
			if(itemStack != null)
			{
				EntityItem item = new EntityItem(worldObj, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
				worldObj.spawnEntityInWorld(item);
			}
		}
		this.inventory.clear();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(list.hasNoTags())
		{
			list = (NBTTagList) compound.getTagList("CauldronItems", Constants.NBT.TAG_COMPOUND);
		}
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) list.get(i);
			int slot = item.getByte("slotNr");
			this.inventory = new InventoryBasic("cauldronInventory", false, 1);
			if(slot >= 0 && slot < inventory.getSizeInventory()) 
			{
				  inventory.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
			this.tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
			if(compound.getTag("CauldronFluid") != null)
			{
				NBTTagCompound fluid = (NBTTagCompound) compound.getTag("CauldronFluid");
				this.tank.setFluid(FluidStack.loadFluidStackFromNBT(fluid));
			}
		}
	}
	
	
	
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack itemStack = inventory.getStackInSlot(i);
			if(itemStack != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("slotNr", (byte) i);
				itemStack.writeToNBT(item);
				list.appendTag(item);
			}
		}
		compound.setTag("CauldronItems", list);
		NBTTagCompound fluid = new NBTTagCompound();
		FluidStack content = this.tank.getFluid();
		if(content != null)
		{
			content.writeToNBT(fluid);
		}
		compound.setTag("CauldronFluid", fluid);
	}

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		int amountFilled = 0;
		
		if(this.tank.getFluid() == null)
		{
			int capacityLeft = this.tank.getCapacity();
			int newAmount = 0;
			int amountLeft = resource.amount;
			while(amountFilled <= capacityLeft && amountLeft > 0)
			{
				
				newAmount++;
				amountLeft--;
				amountFilled++;
			}
			if(doFill)
			{
				this.tank.setFluid(new FluidStack(resource.getFluid(), newAmount));
				resource.amount -= amountFilled;
				return amountFilled;
			}
		}
				
		if(this.tank.getFluidAmount() != this.tank.getCapacity() && resource.isFluidEqual(this.tank.getFluid()))
		{
			int capacityLeft = this.tank.getCapacity() - this.tank.getFluidAmount();
			int newAmount = this.tank.getFluidAmount();
			int amountLeft = resource.amount;
			while(amountFilled <= capacityLeft && amountLeft > 0)
			{
				
				newAmount++;
				amountLeft--;
				amountFilled++;
			}
			if(doFill)
			{
				this.tank.setFluid(new FluidStack(this.tank.getFluid(), newAmount));
				resource.amount -= amountFilled;
			}
		}
		return amountFilled;
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		FluidStack drain = new FluidStack(this.tank.getFluid().getFluid(), 0);
		FluidStack amountLeft = this.tank.getFluid();
		if(!this.tank.getFluid().isFluidEqual(resource)) return drain;
		while(amountLeft.amount > 0 && drain.amount < resource.amount)
		{
			amountLeft.amount--;
			drain.amount++;
		}
		if(doDrain) this.tank.setFluid(amountLeft);
		return drain;
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		FluidStack drain = new FluidStack(this.tank.getFluid().getFluid(), 0);
		FluidStack amountLeft = this.tank.getFluid();
		while(amountLeft.amount > 0 && drain.amount < maxDrain)
		{
			amountLeft.amount--;
			drain.amount++;
		}
		if(doDrain) this.tank.setFluid(amountLeft);
		return drain;
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		if(from == EnumFacing.UP && this.tank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) return true;
		return false;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		if(from != EnumFacing.UP && from != EnumFacing.DOWN && this.tank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		FluidTankInfo[] info = new FluidTankInfo[1];
		info[0] = new FluidTankInfo(this.tank.getFluid(), this.tank.getCapacity());
		return info;
	}
	
}
