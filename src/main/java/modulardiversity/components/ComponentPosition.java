package modulardiversity.components;

import com.google.gson.JsonObject;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.AnchorType;
import modulardiversity.components.requirements.RequirementPosition;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentPosition extends ComponentType<RequirementPosition> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "position";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementPosition provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {

        float xMin = JsonUtil.get(requirement, "xMin", Float.NEGATIVE_INFINITY);
        float xMax = JsonUtil.get(requirement, "xMax", Float.POSITIVE_INFINITY);
        float yMin = JsonUtil.get(requirement, "yMin", Float.NEGATIVE_INFINITY);
        float yMax = JsonUtil.get(requirement, "yMax", Float.POSITIVE_INFINITY);
        float zMin = JsonUtil.get(requirement, "zMin", Float.NEGATIVE_INFINITY);
        float zMax = JsonUtil.get(requirement, "zMax", Float.POSITIVE_INFINITY);
        float distanceMin = JsonUtil.get(requirement, "distanceMin", 0);
        float distanceMax = JsonUtil.get(requirement, "distanceMax", Float.POSITIVE_INFINITY);
        AnchorType anchor = AnchorType.DEFAULT;
        if(requirement.has("anchor")) {
            JsonObject anchorJson = requirement.getAsJsonObject("anchor");
            anchor = AnchorType.get(anchorJson);
        }

        return (RequirementPosition) new RequirementPosition(ioType, xMin, xMax, yMin, yMax, zMin, zMax, distanceMin, distanceMax, anchor).setPerTick(JsonUtil.getPerTick(requirement));

    }
}
