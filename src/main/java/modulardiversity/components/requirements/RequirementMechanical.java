package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType.Registry;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMechanical;
import modulardiversity.jei.ingredients.Mechanical;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementMechanical extends RequirementConsumePerTick<Mechanical,RequirementMechanical.ResourceToken> {
    public final int requiredLevel;
    public final boolean isCrankAllowed;

    public RequirementMechanical(IOType ioType, int requiredLevel, boolean isCrankAllowed) {
        super(Registry.getComponent("mechanical"),ioType);
        this.requiredLevel = requiredLevel;
        this.isCrankAllowed = isCrankAllowed;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementMechanical(getActionType(),requiredLevel,isCrankAllowed);
    }

    @Override
    public ComponentRequirement<Mechanical> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementMechanical(getActionType(), Misc.applyModifiers(modifiers,"mechanical",getActionType(),requiredLevel,false),isCrankAllowed);
    }

    @Override
    public JEIComponent<Mechanical> provideJEIComponent() {
        return new JEIComponentMechanical(this);
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredLevel,isCrankAllowed);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mechanical") &&
                component instanceof MachineComponents.MechanicalHatch &&
                component.getIOType() == getActionType();
    }

    public static class ResourceToken implements IResourceToken {
        private int requiredLevel;
        private boolean isCrankAllowed;
        private boolean requiredlevelMet;

        public ResourceToken(int requiredLevel, boolean isCrankAllowed) {
            this.requiredLevel = requiredLevel;
            this.isCrankAllowed = isCrankAllowed;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }

        public boolean isCrankAllowed() {
            return isCrankAllowed;
        }

        public void setRequiredlevelMet() {
            requiredlevelMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, IOType ioType, float durationMultiplier) {
            requiredLevel = Misc.applyModifiers(modifiers,"mechanical",ioType,requiredLevel,false);
        }

        @Override
        public boolean isEmpty() {
            return requiredlevelMet;
        }
    }
}
