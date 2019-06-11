package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementMineral;
import modulardiversity.util.JsonUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentMineral extends ComponentType<RequirementMineral> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "mineral";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementMineral provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if (requirement.has("name")) {
            String name = JsonUtil.get(requirement,"name",null);
            int oreMin = JsonUtil.get(requirement,"oreMin",0);
            int oreMax = JsonUtil.get(requirement,"oreMax",Integer.MAX_VALUE);
            int amount = JsonUtil.get(requirement,"amount",0);

            return (RequirementMineral) new RequirementMineral(ioType,name,oreMin,oreMax,amount).setPerTick(JsonUtil.getPerTick(requirement));
        } else {
            throw new JsonParseException("The Component 'mineral' expects 'name', 'oreMin', 'oreMax', 'amount'!");
        }
    }
}
