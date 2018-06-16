package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementEmber;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentEmber extends ComponentType<RequirementEmber> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "ember";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementEmber provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("ember") && requirement.get("ember").isJsonPrimitive() && requirement.get("ember").getAsJsonPrimitive().isNumber()) {
            float emberRequired = requirement.getAsJsonPrimitive("ember").getAsFloat();
            return new RequirementEmber(ioType, emberRequired);
        } else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'ember\'-entry that defines the required ember!");
        }
    }
}
