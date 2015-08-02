package mod.mindcraft.steampunk.blocks;

public class BlockFluidPipe extends BlockPipe {
    
    public BlockFluidPipe () {
        super(Material.rock);
    }
    
    
    public TileEntity createNewTileEntity() {
        return new TileFluidPipe();
    }
}