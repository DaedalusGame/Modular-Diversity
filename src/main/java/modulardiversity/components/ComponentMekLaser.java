package modulardiversity.components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.components.requirements.RequirementMekLaser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentMekLaser extends ComponentType<RequirementMekLaser> {
    @Nonnull
    @Override
    public String getRegistryName() {
        return "meklaser";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return null;
    }

    @Nonnull
    @Override
    public RequirementMekLaser provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("energy") && requirement.get("energy").isJsonPrimitive() && requirement.get("energy").getAsJsonPrimitive().isNumber()) {
            double energyRequired = requirement.getAsJsonPrimitive("energy").getAsDouble();
            return new RequirementMekLaser(ioType, energyRequired);
        } else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'energy\'-entry that defines the required Mekanism Laser Energy!");
        }
    }
}
