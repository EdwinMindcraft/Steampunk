package mod.mindcraft.steampunk.tileentities;

public class TilePipe extends TileEntity {
    
    private CoilType type;
    private boolean canImport = false;
    private boolean canExport = false;
    private ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
    private ArrayList<TilePipe> from = new ArrayList<TilePipe>();
    
    public boolean hasCoil() {
        return type != null
    }
    
    public CoilType getCoil() {
        return type;
    }
    
    public void setCoil(CoilType type) {
        this.type = type;
    }
    
    public void importItemsFromNearbyInventories() {
        
        Block blockUp = worldObj.getBlockAt(xCoord, yCoord + 1, zCoord);
        Block blockDown = worldObj.getBlockAt(xCoord, yCoord - 1, zCoord);
        Block blockNorth = worldObj.getBlockAt(xCoord + 1, yCoord, zCoord);
        Block blockSouth = worldObj.getBlockAt(xCoord - 1, yCoord, zCoord);
        Block blockEast = worldObj.getBlockAt(xCoord, yCoord, zCoord + 1);
        Block blockWest = worldObj.getBlockAt(xCoord, yCoord, zCoord - 1);
        
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(blockUp);
        blocks.add(blockDown);
        blocks.add(blockNorth);
        blocks.add(blockSouth);
        blocks.add(blockEast);
        blocks.add(blockWest);
        
        for (Block b : blocks) {
            TileEntity te = worldObj.getTileEntityAt(b.posX, b.posY, b.posZ);
            if ((te != null) && (te instanceof IInventory)) {
                IInventoty inv = (IInventory) te;
                for (int i : inv.getAccessibleSlotsFromSide(0)) {
                    ItemStack tmp = inv.getStackInSlot(i);
                    if (tmp != null && tmp.stackSize > 0) {
                        stacks.add(tmp);
                        inv.decrStackSize(i, tmp.stackSize);
                    }
                }
            }
        }
    }
    
    public void exportItemsToNearbyInventories() {
        
        Block blockUp = worldObj.getBlockAt(xCoord, yCoord + 1, zCoord);
        Block blockDown = worldObj.getBlockAt(xCoord, yCoord - 1, zCoord);
        Block blockNorth = worldObj.getBlockAt(xCoord + 1, yCoord, zCoord);
        Block blockSouth = worldObj.getBlockAt(xCoord - 1, yCoord, zCoord);
        Block blockEast = worldObj.getBlockAt(xCoord, yCoord, zCoord + 1);
        Block blockWest = worldObj.getBlockAt(xCoord, yCoord, zCoord - 1);
        
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(blockUp);
        blocks.add(blockDown);
        blocks.add(blockNorth);
        blocks.add(blockSouth);
        blocks.add(blockEast);
        blocks.add(blockWest);
        
        for (Block b : blocks) {
            TileEntity te = worldObj.getTileEntityAt(b.posX, b.posY, b.posZ);
            if ((te != null) && (te instanceof IInventory)) {
                IInventoty inv = (IInventory) te;
                for (int i : inv.getAccessibleSlotsFromSide(0)) {
                    ItemStack check = inv.getStackInSlot(i);
                    if (check.equals(null) || check.stackSize == 0) {
                        for (int j = 0; j < stacks.size(); j++) {
                            ItemStack tmp = stacks.get(j);
                            stacks.remove(j);
                            inv.setInventorySlotContent(i, tmp);
                        }
                    }
                }
            }
        }
    }
    
    public void transferItemsToNearbyPipe() {
        Block blockUp = worldObj.getBlockAt(xCoord, yCoord + 1, zCoord);
        Block blockDown = worldObj.getBlockAt(xCoord, yCoord - 1, zCoord);
        Block blockNorth = worldObj.getBlockAt(xCoord + 1, yCoord, zCoord);
        Block blockSouth = worldObj.getBlockAt(xCoord - 1, yCoord, zCoord);
        Block blockEast = worldObj.getBlockAt(xCoord, yCoord, zCoord + 1);
        Block blockWest = worldObj.getBlockAt(xCoord, yCoord, zCoord - 1);
        
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(blockUp);
        blocks.add(blockDown);
        blocks.add(blockNorth);
        blocks.add(blockSouth);
        blocks.add(blockEast);
        blocks.add(blockWest);
        
        for (Block b : blocks) {
            Tile te = worldObj.getTileEntityAt(b.posX, b.posY, b.posZ);
            if ((te != null) && (te instanceof TilePipe)) {
                TilePipe pipe = (TilePipe) te;
                for (int i = 0; i < stacks.size(); i++) {
                    ItemStack s = stacks.get(i);
                    if (from.get(i) == null || !from.get(i).equals(this)) {
                        if (from != null)
                            from.remove(i);
                        stacks.remove(i);
                        pipe.receiveItem(s, this);
                    }
                }
            }
        }
    }
    
    public void receiveItem (ItemStack stack, TilePipe from) {
        this.from.set(stacks.size(), from);
        stacks.add(stack);
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        
        if (nbt == null)
            nbt = new NBTTagCompound();
        
        int cType = -1;
        if (type != null)
            cType = type.ordinal();
        
        nbt.setInt("CoilType", cType);
        nbt.setBool("CanImport", canImport);
        nbt.setBool("CanExport", canExport);
        NBTTagList inv = new NBTTagList()
        
        for (ItemStack s : stacks) {
            NBTTagCoumpound tmp = new NBTTagCoumpound();
            tmp.setInt("Count", s.stackSize);
            tmp.setInt("Id", s.itemId);
            tmp.setInt("Meta", s.getItemDamages());
            inv.appendTag(tmp);
        }
        
        nbt.setTag("StoredItem", inv);
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        
        if (nbt == null) return;
        
        int cType = nbt.getInt("CoilType");
        if (cType == -1) {
            type = null;
        } else if (cType < CoilType.values().lenght) {
            type = CoilType.values()[cType];
        } else {
            type = null;
        }
        
        canImport = nbt.getBool("CanImport");
        canExport = nbt.getBool("CanExport");
        
        NBTTagList inv = nbt.getTagList("StoredItem", 10);
        stacks.clear();
        for (int i = 0; i < inv.getTagCount(); i++) {
            NBTTagCompound tmp = inv.getCompoundAt(i);
            int count = tmp.getInt("Count");
            int id = tmp.getInt("Id");
            int meta = tmp.getInt("Meta");
            stacks.add(new ItemStack(id, count, meta));
        }
    }
    
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }
    
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
    
    public void updateEntity() {
        if (canImport)
            importItemsFromNearbyInventories()
        if (canExport)
            exportItemsToNearbyInventories()
    }
}