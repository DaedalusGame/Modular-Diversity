package modulardiversity.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementDaylight;
import modulardiversity.components.requirements.RequirementWeather;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class ComponentWeather extends ComponentType<RequirementWeather> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "weather";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementWeather provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has("weather") && requirement.get("weather").isJsonPrimitive() && requirement.get("weather").getAsJsonPrimitive().isNumber()) {
            int weather = requirement.get("weather").getAsInt();
            return new RequirementWeather(this, ioType, weather);
        } else {
            throw new JsonParseException("The Component 'weather' expects a value between 0-2 that represents the required weather! 2: Thunder | 1: Rain | 0: Clear Skies");
        }
    }
}
