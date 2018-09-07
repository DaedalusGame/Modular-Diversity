package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementLaser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentLaser extends ComponentType<RequirementLaser> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "laser";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementLaser provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("micro-mj") && requirement.get("micro-mj").isJsonPrimitive() && requirement.get("micro-mj").getAsJsonPrimitive().isNumber()) {
            long mjRequired = requirement.getAsJsonPrimitive("micro-mj").getAsLong();
            return new RequirementLaser(ioType, mjRequired);
        } else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'micro-mj\'-entry that defines the required MJ!");
        }
    }
}
