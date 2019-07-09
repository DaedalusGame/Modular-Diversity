package modulardiversity.components;

import com.google.gson.JsonObject;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementAnchor;
import modulardiversity.components.requirements.RequirementAura;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentAnchor extends ComponentType<RequirementAnchor> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "anchor";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementAnchor provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        String name = JsonUtil.get(requirement,"identifier", "");
        int time = JsonUtil.get(requirement,"time",1);

        return (RequirementAnchor) new RequirementAnchor(ioType,name,time).setPerTick(JsonUtil.getPerTick(requirement,true));
    }
}
