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
}