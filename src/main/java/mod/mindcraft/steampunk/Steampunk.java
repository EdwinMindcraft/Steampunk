package mod.mindcraft.steampunk;

import mod.mindcraft.steampunk.utils.*;

@Mod(modid=References.MODID, version="1.0")
public class Steampunk {
    
    public static Block pressurizedFluidPipe;
    public static Block fluidCompressor;
    public static Block alloyMixer;
    public static Block tank;
    public static Block pressurizedItemPipe;
    
    public static Item ironCopperIngot;
    public static Item valve;
    public static Item itemTank;
    
    public static Item coilBase = new Item().setUnlocalizedName(References.COIL_NAME);
    public static Item coilAir = new ItemCoil(CoilType.AIR).setUnlocalizedName(References.COIL_AIR_NAME);
    public static Item coilEarth = new ItemCoil(CoilType.EARTH).setUnlocalizedName(References.COIL_EARTH_NAME);
    public static Item coilFire = new ItemCoil(CoilType.FIRE).setUnlocalizedName(References.COIL_FIRE_NAME);
    public static Item coilIce = new ItemCoil(CoilType.ICE).setUnlocalizedName(References.COIL_ICE_NAME);
    public static Item coilWater = new ItemCoil(CoilType.WATER).setUnlocalizedName(References.COIL_WATER_NAME);
    
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent e) {
    PressurizedFluidRegistry.registerPressurizedFluid(FluidRegistry.WATER, References.PRESSURE_BASE);
        GameRegistry.registerItem (coilBase, "coil");
        GameRegistry.registerItem (coilAir, "coil.air");
        GameRegistry.registerItem (coilEarth, "coil.earth");
        GameRegistry.registerItem (coilFire, "coil.fire");
        GameRegistry.registerItem (coilIce, "coil.ice");
        GameRegistry.registerItem (coilWater, "coil.water");
    }
    
    @EventHandler
    public void init (FMLInitializationEvent e) {
        
    }
}