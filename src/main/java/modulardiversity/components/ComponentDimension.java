package modulardiversity.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementDimension;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class ComponentDimension extends ComponentType<RequirementDimension> {

    public static final String TAG_DIMENSION = "dimension";

    @Nonnull
    @Override
    public String getRegistryName() {
        return "dimension";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementDimension provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has(TAG_DIMENSION) && requirement.get(TAG_DIMENSION).isJsonPrimitive() && requirement.get(TAG_DIMENSION).getAsJsonPrimitive().isNumber()) {
            int biome = requirement.getAsJsonPrimitive(TAG_DIMENSION).getAsInt();
            return new RequirementDimension(ioType, biome);
        } else if (requirement.has(TAG_DIMENSION) && requirement.get(TAG_DIMENSION).isJsonArray()) {
            JsonArray biomes = requirement.get(TAG_DIMENSION).getAsJsonArray();
            ArrayList<Integer> biomesArrayList = new ArrayList<>();
            for (JsonElement i:biomes) {
                if (!(i.isJsonPrimitive() && i.getAsJsonPrimitive().isNumber())) throw new JsonParseException("The ComponentType 'biome' expects an integer-entry that defines the type of biome! Either your array of biomes is wrong or one of your biomes is not an integer!");
                biomesArrayList.add(i.getAsJsonPrimitive().getAsInt());
            }
            return (RequirementDimension) new RequirementDimension(ioType, biomesArrayList).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            throw new JsonParseException("The ComponentType 'biome' expects an integer-entry that defines the type of biome!");
        }
    }
}
