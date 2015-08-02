package mod.mindcraft.steampunk.utils;

public class PressurizedFluidRegistry {
    
    static HashMap<Fluid, float> registry;
    
    public static float getPressureForFluid(Fluid f) {
        if (registry.hasKey(f))
            return registry.get(f);
        return null;
    }
    
    public static void registerPressurizedFluid(Fluid f, float p) {
        registry.put(f, p);
    }
}