package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentEmber;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementEmber extends RequirementConsumeOnce<Embers,RequirementEmber.ResourceToken> {
    public double requiredEmber;

    public RequirementEmber(MachineComponent.IOType actionType, double requiredEmber) {
        super(ComponentType.Registry.getComponent("ember"), actionType);
        this.requiredEmber = requiredEmber;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementEmber(getActionType(),requiredEmber);
    }

    @Override
    public ComponentRequirement<Embers> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementEmber(getActionType(),Misc.applyModifiers(modifiers,"ember",getActionType(),requiredEmber,false));
    }

    @Override
    public JEIComponent<Embers> provideJEIComponent() {
        return new JEIComponentEmber(this);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("ember") &&
                component instanceof MachineComponents.EmberHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredEmber);
    }

    public static class ResourceToken implements IResourceToken
    {
        private double ember;

        public ResourceToken(double ember) {
            this.ember = ember;
        }

        public double getEmber() {
            return ember;
        }

        public void setEmber(double ember) {
            this.ember = ember;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {
            ember = Misc.applyModifiers(modifiers,"ember",ioType,ember,false);
        }

        @Override
        public boolean isEmpty() {
            return ember <= 0;
        }
    }
}
