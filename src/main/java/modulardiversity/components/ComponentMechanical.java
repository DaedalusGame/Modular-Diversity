package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementMechanical;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentMechanical extends ComponentType<RequirementMechanical> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "mechanical";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementMechanical provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("level") && requirement.get("level").isJsonPrimitive() && requirement.get("level").getAsJsonPrimitive().isNumber()) {
            int powerLevel = requirement.getAsJsonPrimitive("level").getAsInt();
            boolean crankAllowed = requirement.has("crank_allowed") && requirement.getAsJsonPrimitive("crank_allowed").getAsBoolean();
            return new RequirementMechanical(ioType, powerLevel, crankAllowed);
        } else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'level\'-entry that defines the required mechanical power level!");
        }
    }
}
