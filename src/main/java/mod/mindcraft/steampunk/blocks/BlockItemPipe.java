package mod.mindcraft.steampunk.blocks;

public class BlockItemPipe extends BlockPipe {
    
    public BlockItemPipe() {
        super(Material.rock);
    }
    
 
    public TileEntity createNewTileEntity() {
        return new TileItemPipe();
    }
}