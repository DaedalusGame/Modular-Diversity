package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents.BiomeDetector;
import modulardiversity.components.MachineComponents.DaylightDetector;
import modulardiversity.components.ComponentDaylight;
import modulardiversity.jei.JEIComponentDaylight;
import modulardiversity.jei.ingredients.DaylightIngredient;

import java.util.ArrayList;
import java.util.List;

public class RequirementDaylight extends ComponentRequirement<DaylightIngredient> {

    private ArrayList<Integer> daylight;

    public RequirementDaylight(ComponentType componentType, MachineComponent.IOType actionType, ArrayList<Integer> time) {
        super(componentType, MachineComponent.IOType.INPUT);
        this.daylight = time;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof DaylightDetector && component.getIOType() == this.getActionType()) {
            long trueDaylight = ((DaylightDetector) component).getContainerProvider();
            int tempDaylightEnd = daylight.get(1);
            if (daylight.get(0) > tempDaylightEnd) {
                tempDaylightEnd += 24000;
                if (trueDaylight < daylight.get(0)) trueDaylight += 24000;
            }
            return this.daylight.get(0) < trueDaylight && trueDaylight < tempDaylightEnd;
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof BiomeDetector && component.getIOType() == this.getActionType()) {
            return true;
        }
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof DaylightDetector && component.getIOType() == this.getActionType()) {
            long trueDaylight = ((DaylightDetector) component).getContainerProvider();
            int tempDaylightEnd = daylight.get(1);
            if (daylight.get(0) > tempDaylightEnd) {
                tempDaylightEnd += 24000;
                if (trueDaylight < daylight.get(0)) trueDaylight += 24000;
            }
            return this.daylight.get(0) < trueDaylight && trueDaylight < tempDaylightEnd ? CraftCheck.SUCCESS : CraftCheck.PARTIAL_SUCCESS;
        }
        return CraftCheck.INVALID_SKIP;
    }

    @Override
    public ComponentRequirement deepCopy() {
        RequirementDaylight requirementDaylight = new RequirementDaylight(new ComponentDaylight(), this.getActionType(), this.daylight);
        return requirementDaylight;
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }

    @Override
    public JEIComponent<DaylightIngredient> provideJEIComponent() {
        return new JEIComponentDaylight(this);
    }

    public ArrayList<Integer> getDaylight() {
        return this.daylight;
    }
}
