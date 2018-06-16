package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.jei.ingredients.Air;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementAir extends ComponentRequirement.PerTick<Air> {
    public final int volumeConsumed;
    public final float pressureRequired;

    public RequirementAir(ComponentType componentType, MachineComponent.IOType actionType, int volumeConsumed, float pressureRequired) {
        super(componentType, actionType);
        this.volumeConsumed = volumeConsumed;
        this.pressureRequired = pressureRequired;
    }

    @Override
    public void startIOTick(RecipeCraftingContext recipeCraftingContext, float v) {

    }

    @Override
    public void resetIOTick(RecipeCraftingContext recipeCraftingContext) {

    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext) {
        return null;
    }

    @Override
    public boolean startCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, List<ComponentOutputRestrictor> list) {
        return null;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return null;
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {

    }

    @Override
    public void endRequirementCheck() {

    }

    @Override
    public JEIComponent<Air> provideJEIComponent() {
        return null;
    }
}
