package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;

public class BiomeIngredient implements IFakeIngredient {
    private ArrayList<Integer> biomes;

    public BiomeIngredient(ArrayList<Integer> biomes) {
        this.biomes = biomes;
    }

    public ArrayList<Integer> getBiomes(int index) {
        return biomes;
    }

    @Override
    public String getDisplayName() {
        String displayName = "";
        for (Integer i:biomes) {
            displayName += Biome.getBiome(i).getBiomeName() + " or ";
        }
        displayName = displayName.substring(0,displayName.length()-4);
        return displayName;
    }

    @Override
    public String getUniqueID() {
        return "biome";
    }
}
