package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMana;
import modulardiversity.jei.ingredients.Mana;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementMana extends RequirementConsumeOnce<Mana,RequirementMana.ResourceToken> {
    public int requiredMana;

    public RequirementMana(IOType actionType, int requiredMana) {
        super(ComponentType.Registry.getComponent("mana"), actionType);
        this.requiredMana = requiredMana;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mana") &&
                component instanceof MachineComponents.ManaHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredMana);
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementMana(getActionType(),requiredMana);
    }

    @Override
    public ComponentRequirement<Mana> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementMana(getActionType(),Misc.applyModifiers(modifiers,"mana",getActionType(),requiredMana,false));
    }

    @Override
    public JEIComponent<Mana> provideJEIComponent() {
        return new JEIComponentMana(this);
    }

    public static class ResourceToken implements IResourceToken {
        private int mana;

        public ResourceToken(int mana) {
            this.mana = mana;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, IOType ioType, float durationMultiplier) {
            mana = Misc.applyModifiers(modifiers,"mana",ioType,mana,false);
        }

        @Override
        public boolean isEmpty() {
            return mana <= 0;
        }
    }
}
