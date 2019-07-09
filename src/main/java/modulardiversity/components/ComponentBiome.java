package modulardiversity.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementBiome;
import modulardiversity.util.JsonUtil;

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
        //TODO: clean up busted english or just yeet the error messages and "hardcrash" instead
        if (requirement.has("biome") && requirement.get("biome").isJsonPrimitive() && requirement.get("biome").getAsJsonPrimitive().isString()) {
            String biome = requirement.getAsJsonPrimitive("biome").getAsString();
            return new RequirementBiome(ioType, biome);
        } else if (requirement.has("biome") && requirement.get("biome").isJsonArray()) {
            JsonArray biomes = requirement.get("biome").getAsJsonArray();
            ArrayList<String> biomesArrayList = new ArrayList<>();
            for (JsonElement i:biomes) {
                if (!i.isJsonPrimitive() || !i.getAsJsonPrimitive().isString()) throw new JsonParseException("The ComponentType 'biome' expects a string-entry that defines the type of biome! Either your array of biomes is wrong or one of your biomes is not a string!");
                biomesArrayList.add(i.getAsJsonPrimitive().getAsString());
            }
            return (RequirementBiome) new RequirementBiome(ioType, biomesArrayList).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            throw new JsonParseException("The ComponentType 'biome' expects a string-entry that defines the type of biome!");
        }
    }
}
