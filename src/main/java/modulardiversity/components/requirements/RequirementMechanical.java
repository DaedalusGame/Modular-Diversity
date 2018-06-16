package modulardiversity.components.requirements;

import betterwithmods.api.tile.IMechanicalPower;
import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.ComponentType.Registry;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMechanical;
import modulardiversity.jei.ingredients.Mechanical;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementMechanical extends ComponentRequirement.PerTick<Mechanical> {
    public final int requiredLevel;
    public final boolean isCrankAllowed;
    private int requiredIO;

    public RequirementMechanical(IOType ioType, int requiredLevel, boolean isCrankAllowed) {
        super(Registry.getComponent("mechanical"),ioType);
        this.requiredLevel = requiredLevel;
        this.isCrankAllowed = isCrankAllowed;
    }

    @Override
    public boolean startCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return this.canStartCrafting(machineComponent, recipeCraftingContext, Lists.newArrayList()) == CraftCheck.SUCCESS;
    }

    @Override
    public boolean finishCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return true;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, List<ComponentOutputRestrictor> list) {
        if(machineComponent.getComponentType().equals(this.getRequiredComponentType()) && machineComponent instanceof MachineComponents.MechanicalHatch && machineComponent.getIOType() == getActionType())
        {
            CraftCheck x = getCraftCheck(machineComponent, recipeCraftingContext);
            if (x != null) return x;
        }

        return CraftCheck.INVALID_SKIP;
    }

    private CraftCheck getCraftCheck(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext) {
        IMechanicalPower mechanicalPower = (IMechanicalPower) recipeCraftingContext.getProvidedCraftingComponent(machineComponent);
        if(mechanicalPower == null)
            return CraftCheck.FAILURE_MISSING_INPUT;
        switch(getActionType()) {
            case INPUT:
                if(mechanicalPower.calculateInput() >= recipeCraftingContext.applyModifiers(this, getActionType(), requiredLevel, false))
                    return CraftCheck.SUCCESS;
                break;
            case OUTPUT:
                return CraftCheck.SUCCESS;
            default:
                return CraftCheck.FAILURE_MISSING_INPUT;
        }
        return null;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementMechanical(getActionType(),requiredLevel,isCrankAllowed);
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {}

    @Override
    public void endRequirementCheck() {}

    @Override
    public JEIComponent<Mechanical> provideJEIComponent() {
        return new JEIComponentMechanical(this);
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
        if(machineComponent.getComponentType().equals(this.getRequiredComponentType()) && machineComponent instanceof MachineComponents.MechanicalHatch && machineComponent.getIOType() == getActionType())
        {
            CraftCheck x = getCraftCheck(machineComponent, recipeCraftingContext);
            if (x != null) return x;
        }

        return CraftCheck.INVALID_SKIP;
    }
}
