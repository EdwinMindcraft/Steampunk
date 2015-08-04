package mod.mindcraft.steampunk.utils;

public enum CoilType {
    AIR,
    EARTH,
    FIRE,
    ICE,
    WATER;
    
    public getType () {
        return getName().toLowerCase();
    }
}