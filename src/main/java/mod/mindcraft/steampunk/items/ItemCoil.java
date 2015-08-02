package mod.mindcraft.steampunk.items;

import mod.mindcraft.steampunl.utils.CoilType;

public class ItemCoil extends Item {
    
    private CoilType type;
    
    public ItemCoil (CoilType type) {
        this.type = type;
    }
    
    
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister ir) {
        itemIcon = ir.registerIcon(References.MODID + ":coil" + type.getType());
    }
    
    public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            return false;
        
        Block block = world.getBlockAt(x, y, z);
        if (!(block instanceof BlockPipe))
            return false;
        
        TileEntity te = world.getTileEntityAt(x, y, z);
        
        if (!(te instanceof TilePipe))
            return false;
        
        TilePipe tip = (TilePipe) te;
        
        if (tip.hasCoil())
            return false;
        
        tip.setCoil(type);
        tip.markDirty();
        
        return true;
    }
}