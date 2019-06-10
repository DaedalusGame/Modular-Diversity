package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementModifier;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentModifier extends ComponentType<RequirementModifier> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "modifier";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementModifier provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has("name") && requirement.has("min") || requirement.has("max")) {
            String name = JsonUtil.get(requirement,"name",null);
            float min = JsonUtil.get(requirement,"min",Float.NEGATIVE_INFINITY);
            float max = JsonUtil.get(requirement,"max",Float.POSITIVE_INFINITY);

            return (RequirementModifier) new RequirementModifier(ioType,name,min,max).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            throw new JsonParseException("The Component 'modifier' expects 'name', 'min', 'max'!");
        }
    }
}
