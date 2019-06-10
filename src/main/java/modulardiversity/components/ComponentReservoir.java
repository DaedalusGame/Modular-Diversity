package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentReservoir extends ComponentType<RequirementReservoir> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "reservoir";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementReservoir provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has("name")) {
            String name = JsonUtil.get(requirement,"name",null);
            int fluidMin = JsonUtil.get(requirement,"fluidMin",0);
            int fluidMax = JsonUtil.get(requirement,"fluidMax",Integer.MAX_VALUE);
            int residualMin = JsonUtil.get(requirement,"residualMin",0);
            int residualMax = JsonUtil.get(requirement,"residualMax",Integer.MAX_VALUE);
            int amount = JsonUtil.get(requirement,"amount",0);

            return (RequirementReservoir) new RequirementReservoir(ioType,name,fluidMin,fluidMax,residualMin,residualMax,amount).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            throw new JsonParseException("The Component 'reservoir' expects 'name', 'fluidMin', 'fluidMax', 'residualMin', 'residualMax', 'amount'!");
        }
    }
}
