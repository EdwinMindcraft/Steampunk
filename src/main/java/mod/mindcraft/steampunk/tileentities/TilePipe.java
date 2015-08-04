package mod.mindcraft.steampunk.tileentities;

public class TilePipe extends TileEntity {
    
    private CoilType type;
    
    public boolean hasCoil() {
        return type != null
    }
    
    public CoilType getCoil() {
        return type;
    }
    
    public void setCoil(CoilType type) {
        this.type = type;
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        
        if (nbt == null)
            nbt = new NBTTagCompound();
        
        int cType = -1;
        if (type != null)
            cType = type.ordinal();
        
        nbt.setInt("CoilType", cType);
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
    }
    
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }
    
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
    
}