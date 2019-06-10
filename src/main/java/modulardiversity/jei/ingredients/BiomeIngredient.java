package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;
import modulardiversity.util.DimensionNameUtil;
import modulardiversity.util.Misc;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BiomeIngredient implements IFakeIngredient {
    private Collection<Integer> biomes;

    public BiomeIngredient(Collection<Integer> biomes) {
        this.biomes = biomes;
    }

    public Collection<Integer> getBiomes(int index) {
        return biomes;
    }

    @Override
    public String getDisplayName() {
        return biomes.stream().map(Biome::getBiome).filter(Objects::nonNull).map(Biome::getBiomeName).collect(Misc.englishList());
    }

    @Override
    public String getUniqueID() {
        return "biome";
    }
}
