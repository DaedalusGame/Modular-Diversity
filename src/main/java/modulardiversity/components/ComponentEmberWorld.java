package modulardiversity.components;

import com.google.gson.JsonObject;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementAura;
import modulardiversity.components.requirements.RequirementEmberWorld;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentEmberWorld extends ComponentType<RequirementEmberWorld> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "mantle";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementEmberWorld provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {

            float emberMin = JsonUtil.get(requirement,"emberMin",0f);
            float emberMax = JsonUtil.get(requirement,"emberMax", Float.MAX_VALUE);
            float stabilityMin = JsonUtil.get(requirement,"stabilityMin",0f);
            float stabilityMax = JsonUtil.get(requirement,"stabilityMax", Float.MAX_VALUE);

            return (RequirementEmberWorld) new RequirementEmberWorld(ioType,emberMin,emberMax,stabilityMin,stabilityMax).setPerTick(JsonUtil.getPerTick(requirement));
    }
}
