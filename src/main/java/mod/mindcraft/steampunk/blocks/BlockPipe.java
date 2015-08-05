package mod.mindcraft.steampunk.blocks;

public class BlockPipe extends BlockContainer {
    
    public BlockPipe() {
        super(Material.rock);
    }
    
    public TileEntity createNewTileEntity() {
        return new TilePipe();
    }
}