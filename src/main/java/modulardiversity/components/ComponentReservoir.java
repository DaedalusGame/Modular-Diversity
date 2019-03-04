package modulardiversity.components;

import com.google.gson.JsonObject;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementReservoir;

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
    public RequirementReservoir provideComponent(MachineComponent.IOType ioType, JsonObject jsonObject) {
        return null;
    }
}
