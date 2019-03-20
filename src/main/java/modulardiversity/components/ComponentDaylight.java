package modulardiversity.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementDaylight;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class ComponentDaylight extends ComponentType<RequirementDaylight> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "daylight";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementDaylight provideComponent(MachineComponent.IOType ioType, JsonObject json) {
        JsonUtil.assertOnlyOne(json,"daytime","time","");

        if (json.has("daylight") && json.get("daylight").isJsonArray() && json.get("daylight").getAsJsonArray().size() == 2 && json.get("daylight").getAsJsonArray().get(0).isJsonPrimitive() && json.get("daylight").getAsJsonArray().get(1).isJsonPrimitive() && json.get("daylight").getAsJsonArray().get(0).getAsJsonPrimitive().isNumber() && json.get("daylight").getAsJsonArray().get(1).getAsJsonPrimitive().isNumber()) {
            JsonArray daylightPrimitive = json.get("daylight").getAsJsonArray();
            ArrayList<Integer> daylight = new ArrayList<>();
            for (JsonElement i: daylightPrimitive) daylight.add(i.getAsInt());
            return new RequirementDaylight(ioType, 0,0,0);
        } else {
            throw new JsonParseException("The ComponentType 'daylight' expects an array of length 2 that defines the earliest time and latest time the recipe can occur! Example: [6000, 1[2000] (Noon to Dusk)");
        }
    }
}
