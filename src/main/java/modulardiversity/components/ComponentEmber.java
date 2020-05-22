package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementEmber;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentEmber extends ComponentType {
    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }
}
