package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;
import modulardiversity.util.DimensionNameUtil;
import modulardiversity.util.Misc;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BiomeIngredient implements IFakeIngredient {
    private Collection<String> biomes;

    public BiomeIngredient(Collection<String> biomes) {
        this.biomes = biomes;
    }

    public Collection<String> getBiomes() {
        return biomes;
    }

    @Override
    public String getDisplayName() {
        return biomes.stream().map(identifier -> Biome.REGISTRY.getObject(new ResourceLocation(identifier))).filter(Objects::nonNull).map(Biome::getBiomeName).collect(Misc.englishList());
    }

    @Override
    public String getUniqueID() {
        return "biome";
    }
}
