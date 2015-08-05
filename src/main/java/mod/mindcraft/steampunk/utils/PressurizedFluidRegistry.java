package mod.mindcraft.steampunk.utils;

public class PressurizedFluidRegistry {
    
    static HashMap<Fluid, Pressure> registry;
    
    public static Pressure getPressureForFluid(Fluid f) {
        if (registry.hasKey(f))
            return registry.get(f);
        return null;
    }
    
    public static void registerPressurizedFluid(Fluid f, float pMin, float pMax) {
        registry.put(f, new Pressure(pMin, pMax));
    }
    
    public class Pressure {
        
        public float pMin;
        public float pMax;
        
        public Pressure (float pMin, float pMax) {
            this.pMin = pMin;
            this.pMax = pMax;
        }
        
    }
}