package modulardiversity.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementBiome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class ComponentBiome extends ComponentType<RequirementBiome> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "biome";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementBiome provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has("biome") && requirement.get("biome").isJsonPrimitive() && requirement.get("biome").getAsJsonPrimitive().isNumber()) {
            int biome = requirement.getAsJsonPrimitive("biome").getAsInt();
            return new RequirementBiome(this, ioType, biome);
        } else if (requirement.has("biome") && requirement.get("biome").isJsonArray()) {
            JsonArray biomes = requirement.get("biome").getAsJsonArray();
            ArrayList<Integer> biomesArrayList = new ArrayList<>();
            for (JsonElement i:biomes) {
                if (!(i.isJsonPrimitive() && i.getAsJsonPrimitive().isNumber())) throw new JsonParseException("The ComponentType 'biome' expects an integer-entry that defines the type of biome! Either your array of biomes is wrong or one of your biomes is not an integer!");
                biomesArrayList.add(i.getAsJsonPrimitive().getAsInt());
            }
            return new RequirementBiome(this, ioType, biomesArrayList);
        } else {
            throw new JsonParseException("The ComponentType 'biome' expects an integer-entry that defines the type of biome!");
        }
    }
}
