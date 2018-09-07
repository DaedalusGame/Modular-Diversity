package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.MachineRecipe;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentLaser;
import modulardiversity.jei.ingredients.Laser;
import modulardiversity.util.ICraftingResourceHolder;
import modulardiversity.util.IResourceToken;

public class RequirementLaser extends RequirementConsumePerTick<Laser,RequirementLaser.ResourceToken> {
    public static long highestRequiredMJ;

    public long requiredMicroMJ;

    public RequirementLaser(MachineComponent.IOType actionType, long requiredMicroMJ) {
        super(ComponentType.Registry.getComponent("laser"), actionType);
        this.requiredMicroMJ = requiredMicroMJ;
        if(requiredMicroMJ > highestRequiredMJ)
            highestRequiredMJ = requiredMicroMJ;
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementLaser(getActionType(),requiredMicroMJ);
    }

    @Override
    public JEIComponent<Laser> provideJEIComponent() {
        return new JEIComponentLaser(this);
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance resultChance) {
        if(component instanceof MachineComponents.LaserHatch) {
            ICraftingResourceHolder<RequirementLaser.ResourceToken> handler = (ICraftingResourceHolder<RequirementLaser.ResourceToken>) context.getProvidedCraftingComponent(component);
        }
        return true;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("laser") &&
                component instanceof MachineComponents.LaserHatch &&
                component.getIOType() == getActionType();
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredMicroMJ, context != null ? context.getParentRecipe() : null);
    }

    public static class ResourceToken implements IResourceToken
    {
        long requiredMicroMJ;
        MachineRecipe recipe;

        public ResourceToken(long requiredMicroMJ, MachineRecipe recipe) {
            this.requiredMicroMJ = requiredMicroMJ;
            this.recipe = recipe;
        }

        public MachineRecipe getRecipe() {
            return recipe;
        }

        public long getRequiredMicroMJ() {
            return requiredMicroMJ;
        }

        public void setRequiredMicroMJ(long requiredMicroMJ) {
            this.requiredMicroMJ = requiredMicroMJ;
        }

        @Override
        public float getModifier() {
            return requiredMicroMJ;
        }

        @Override
        public void setModifier(float modifier) {
            requiredMicroMJ = (long) modifier;
        }

        @Override
        public boolean isEmpty() {
            return requiredMicroMJ <= 0;
        }
    }
}
