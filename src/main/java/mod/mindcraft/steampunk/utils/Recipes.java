package mod.mindcraft.steampunk.utils;

public class Recipes {
    
    public static void addRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(Steampunk.rustyGear), new Object[] {
            " I ",
            "IWI",
            " I ",
            'I', "ingotIron",
            'W', Items.bucket_water
        });
    }
    
}