package modulardiversity.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.components.requirements.RequirementMekHeat;


public class ComponentHotAir extends ComponentType<RequirementHotAir> {
	@Nonnull
    @Override
    public String getRegistryName() {
        return "hotair";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }
    
    @Nonnull
    @Override
    public RequirementHotAir provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        int temperatureRequiredMin = requirement.has("temperatureRequiredMin") ? requirement.getAsJsonPrimitive("temperatureRequiredMin").getAsInt() : 0;
        int temperatureRequiredMax = requirement.has("temperatureRequiredMax") ? requirement.getAsJsonPrimitive("temperatureRequiredMax").getAsInt() : Integer.MAX_VALUE;
        int temperature = requirement.has("temperature") ? requirement.getAsJsonPrimitive("temperature").getAsInt() : 0;

        return new RequirementHotAir(ioType, temperatureRequiredMin, temperatureRequiredMax, temperature);
    }
}
