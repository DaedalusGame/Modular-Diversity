package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementAura;
import modulardiversity.components.requirements.RequirementMineral;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentAura extends ComponentType<RequirementAura> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "vis";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementAura provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {

            float visMin = JsonUtil.get(requirement,"visMin",0f);
            float visMax = JsonUtil.get(requirement,"visMax", Float.MAX_VALUE);
            float vis = JsonUtil.get(requirement,"vis",0f);
            float fluxMin = JsonUtil.get(requirement,"fluxMin",0f);
            float fluxMax = JsonUtil.get(requirement,"fluxMax", Float.MAX_VALUE);
            float flux = JsonUtil.get(requirement,"flux",0f);

            return (RequirementAura) new RequirementAura(ioType,visMin,visMax,fluxMin,fluxMax,vis,flux).setPerTick(JsonUtil.getPerTick(requirement));
    }
}
