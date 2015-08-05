package mod.mindcraft.steampunk;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import mod.mindcraft.steampunk.utils.*;

@Mod(modid=References.MODID, version="1.0")
public class Steampunk {
    
    public static Block pressurizedPipe = new BlockPipe();
    public static Block fluidCompressor;
    public static Block alloyMixer;
    public static Block tank;
    public static Block pressurizedItemPipe;
    
    public static Fluid GAZ_WATER = new Fluid("gas_water");
    
    public static Item ironCopperIngot = new Item().setUnlocalizedName(References.IRON_COPPER_INGOT_NAME).setTextureName(References.MODID + ":ironcopperIingot");
    public static Item steelIngot = new Item().setUnlocalizedName(References.STEEL_INGOT_NAME).setTextureName(References.MODID + ":steelIngot");
    public static Item copperIngot = new Item().setUnlocalizedName(References.COPPER_INGOT_NAME).setTextureName(References.MODID + "copperIngot");
    
    public static Item valve = new Item().setUnlocalizedName(References.VALVE_NAME).setTextureName(References.MODID + ":valve");
    public static Item itemTank = new Item().setUnlocalizedName(References.ITEM_TANK_NAME).setTextureName(References.MODID + ":itemTank");
    public static Item rustyGear = new Item().setUnlocalizedName(References.RUSTY_GEAR_NAME).setTextureName(References.MODID + ":rustyGear");
    
    public static Item coilBase = new Item().setUnlocalizedName(References.COIL_NAME).setTextureName(References.MODID + ":coil");
    public static Item coilAir = new ItemCoil(CoilType.AIR).setUnlocalizedName(References.COIL_AIR_NAME);
    public static Item coilEarth = new ItemCoil(CoilType.EARTH).setUnlocalizedName(References.COIL_EARTH_NAME);
    public static Item coilFire = new ItemCoil(CoilType.FIRE).setUnlocalizedName(References.COIL_FIRE_NAME);
    public static Item coilIce = new ItemCoil(CoilType.ICE).setUnlocalizedName(References.COIL_ICE_NAME);
    public static Item coilWater = new ItemCoil(CoilType.WATER).setUnlocalizedName(References.COIL_WATER_NAME);
    
    @SidedProxy(clientSide="mod.mindcraft.steampunk.client.ClientProxy", serverSide="mod.mindcraft.steampunk.CommonProxy")
    public CommonProxy proxy;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        
        proxy.registerRender();
        
        PressurizedFluidRegistry.registerPressurizedFluid(FluidRegistry.WATER, References.PRESSURE_BASE, References.PRESSURE_BASE*10);
        PressurizedFluidRegistry.registerPressurizedFluid(GAZ_WATER, References.PRESSURE_BASE/10, References.PRESSURE_BASE);
        PressurizedFluidRegistry.registerPressurizedFluid(FluidRegistry.LAVA, References.PRESSURE_BASE / 2, References.PRESSURE_BASE*2);
        
        GameRegistry.registerItem(coilBase, "coil");
        GameRegistry.registerItem(coilAir, "coil.air");
        GameRegistry.registerItem(coilEarth, "coil.earth");
        GameRegistry.registerItem(coilFire, "coil.fire");
        GameRegistry.registerItem(coilIce, "coil.ice");
        GameRegistry.registerItem(coilWater, "coil.water");
        
        GameRegistry.registerItem(ironCopperIngot, "iron.copper.ingot");
        GameRegistry.registerItem(steelIngot, "steel.ingot");
        GameRegistry.registerItem(copperIngot, "copper.ingot");
        
        GameRegistry.registerItem(valve, "valve");
        GameRegistry.registerItem(itemTank, "tank.item");
        GameRegistry.registerItem(rustyGear, "rusty.gear");
    }
    
    @EventHandler
    public void init (FMLInitializationEvent e) {
        OreDictionary.registerOre("ingotSteel", steelIngot);
        OreDictionary.registerOre("ingotCopper", copperIngot);
        OreDictionary.registerOre("ingotReinforcedCopper", ironCopperIngot);
        
        Recipes.addRecipes();
    }
}