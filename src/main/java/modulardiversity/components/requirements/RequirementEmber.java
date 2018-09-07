package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentEmber;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.util.IResourceToken;

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
        public float getModifier() {
            return (float)ember;
        }

        @Override
        public void setModifier(float modifier) {
            ember = modifier;
        }

        @Override
        public boolean isEmpty() {
            return ember <= 0;
        }
    }
}
