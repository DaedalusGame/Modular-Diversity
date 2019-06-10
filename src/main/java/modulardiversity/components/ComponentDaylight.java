package modulardiversity.components;

import com.google.gson.*;
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
        if (json.has("start") || json.has("stop")) {
            long start = 0;
            long stop = Long.MAX_VALUE;
            long modulo = Long.MAX_VALUE;
            boolean local = false;
            if(json.has("start"))
                start = json.getAsJsonPrimitive("start").getAsLong();
            if(json.has("stop"))
                stop = json.getAsJsonPrimitive("stop").getAsLong();
            if(json.has("modulo")) {
                modulo = json.getAsJsonPrimitive("modulo").getAsLong();
            }
            if(json.has("local")) {
                local = json.getAsJsonPrimitive("local").getAsBoolean();
            }
            return (RequirementDaylight) new RequirementDaylight(ioType, start,stop,modulo,local).setPerTick(JsonUtil.getPerTick(json));
        } else {
            throw new JsonParseException("The ComponentType 'daylight' expects 'start', 'stop' and optionally 'modulo'. Refer to the documentation for how they work together.");
        }
    }
}
