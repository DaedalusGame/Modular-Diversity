package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementWeather;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (requirement.has("weather") && requirement.get("weather").isJsonPrimitive() && requirement.get("weather").getAsJsonPrimitive().isString()) {
            String weather = requirement.get("weather").getAsString();
            return (RequirementWeather) new RequirementWeather(ioType, RequirementWeather.Type.valueOf(weather)).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            Stream<String> possibleValues = Arrays.stream(RequirementWeather.Type.values()).map(Enum::name);
            throw new JsonParseException("The Component 'weather' expects a string! One of: "+String.join(", ", (Iterable<String>) possibleValues::iterator));
        }
    }
}
