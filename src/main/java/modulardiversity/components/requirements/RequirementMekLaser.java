package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentMekLaser;
import modulardiversity.jei.ingredients.MekLaser;
import modulardiversity.util.IResourceToken;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

public class RequirementMekLaser extends RequirementConsumeOnce<MekLaser, RequirementMekLaser.ResourceToken> {

    public double requiredEnergy;

    public RequirementMekLaser(MachineComponent.IOType actionType, double requiredEnergy) {
        super(ComponentType.Registry.getComponent("mlenergy"), actionType);
        this.requiredEnergy = requiredEnergy;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("mlenergy") &&
                component instanceof MachineComponents.MekLaserAcceptor &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredEnergy);
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementMekLaser(getActionType(), requiredEnergy);
    }

    @Override
    public JEIComponent<MekLaser> provideJEIComponent() {
        return new JEIComponentMekLaser(this);
    }

    public static class ResourceToken implements IResourceToken {
        double energy;

        public ResourceToken(double energy) {
            this.energy = energy;
        }

        public double getEnergy() {
            return energy;
        }

        public void setEnergy(double energy) {
            this.energy = energy;
        }

        @Override
        public float getModifier() {
            return (float) energy;
        }

        @Override
        public void setModifier(float modifier) {
            energy = (double) modifier;
        }

        @Override
        public boolean isEmpty() {
            return energy <= 0;
        }
    }
}
