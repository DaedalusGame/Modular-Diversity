package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMysticalMechanics;
import modulardiversity.jei.ingredients.MysticalMechanics;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementMysticalMechanics extends RequirementConsumePerTick<MysticalMechanics,RequirementMysticalMechanics.ResourceToken> {
    private double requiredLevelMin;
    private double requiredLevelMax;
    private double levelOutput;

    public RequirementMysticalMechanics(MachineComponent.IOType actionType, double requiredLevelMin, double requiredLevelMax) {
        this(actionType,requiredLevelMin,requiredLevelMax,0);
    }

    public RequirementMysticalMechanics(MachineComponent.IOType actionType, double levelOutput) {
        this(actionType,0,0,levelOutput);
    }

    public RequirementMysticalMechanics(MachineComponent.IOType actionType, double requiredLevelMin, double requiredLevelMax, double levelOutput) {
        super(ComponentType.Registry.getComponent("mysticalmechanics"), actionType);
        this.requiredLevelMin = requiredLevelMin;
        this.requiredLevelMax = requiredLevelMax;
        this.levelOutput = levelOutput;
    }

    public double getRequiredLevelMin() {
        return requiredLevelMin;
    }

    public double getRequiredLevelMax() {
        return requiredLevelMax;
    }

    public double getLevelOutput() {
        return levelOutput;
    }

    @Override
    protected RequirementMysticalMechanics.ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredLevelMin,requiredLevelMax,levelOutput);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mysticalmechanics") &&
                component instanceof MachineComponents.MysticalMechanicsHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    public ComponentRequirement<MysticalMechanics> deepCopy() {
        return new RequirementMysticalMechanics(getActionType(),requiredLevelMin,requiredLevelMax,levelOutput);
    }

    @Override
    public ComponentRequirement<MysticalMechanics> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementMysticalMechanics(getActionType(),
                Misc.applyModifiers(modifiers,"mysticalmechanics_min",getActionType(), requiredLevelMin,false),
                Misc.applyModifiers(modifiers,"mysticalmechanics_max",getActionType(), requiredLevelMax,false),
                Misc.applyModifiers(modifiers,"mysticalmechanics",getActionType(), levelOutput,false)
        );
    }

    @Override
    public JEIComponent<MysticalMechanics> provideJEIComponent() {
        return new JEIComponentMysticalMechanics(this);
    }

    public static class ResourceToken implements IResourceToken {
        private double requiredLevelMin;
        private double requiredLevelMax;
        private double levelOutput;
        private boolean requiredlevelMet;

        public ResourceToken(double requiredLevelMin, double requiredLevelMax, double levelOutput) {
            this.requiredLevelMin = requiredLevelMin;
            this.requiredLevelMax = requiredLevelMax;
            this.levelOutput = levelOutput;
        }

        public double getRequiredLevelMin() {
            return requiredLevelMin;
        }

        public double getRequiredLevelMax() {
            return requiredLevelMax;
        }

        public double getLevelOutput() {
            return levelOutput;
        }

        public void setRequiredlevelMet() {
            requiredlevelMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            requiredLevelMin = Misc.applyModifiers(modifiers,"mysticalmechanics_min",ioType, requiredLevelMin,false);
            requiredLevelMax = Misc.applyModifiers(modifiers,"mysticalmechanics_max",ioType, requiredLevelMax,false);
            levelOutput = Misc.applyModifiers(modifiers,"mysticalmechanics",ioType, levelOutput,false);
        }

        @Override
        public boolean isEmpty() {
            return requiredlevelMet;
        }
    }
}
