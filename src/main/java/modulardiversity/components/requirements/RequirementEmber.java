package modulardiversity.components.requirements;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentEmber;
import modulardiversity.jei.ingredients.Embers;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementEmber extends ComponentRequirement<Embers> {
    public float requiredEmber;

    private float requirementCheck;

    public RequirementEmber(MachineComponent.IOType actionType, double requiredEmber) {
        super(ComponentType.Registry.getComponent("ember"), actionType);
    }

    @Override
    public boolean startCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return canStartCrafting(machineComponent,recipeCraftingContext, Lists.newArrayList()) == CraftCheck.SUCCESS;
    }

    @Override
    public boolean finishCrafting(MachineComponent machineComponent, RecipeCraftingContext recipeCraftingContext, ResultChance resultChance) {
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext recipeCraftingContext, List<ComponentOutputRestrictor> list) {
        if(component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof MachineComponents.EmberHatch && component.getIOType() == this.getActionType()) {
            switch (getActionType()) {
                case INPUT:
                    if(requirementCheck <= 0)
                        return CraftCheck.SUCCESS;
                    break;
                case OUTPUT:
                    return CraftCheck.SUCCESS;
            }

            return CraftCheck.FAILURE_MISSING_INPUT;
        } else {
            return CraftCheck.INVALID_SKIP;
        }
    }

    @Override
    public ComponentRequirement deepCopy() {
        return new RequirementEmber(getActionType(),requiredEmber);
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
        requirementCheck = recipeCraftingContext.applyModifiers(this,getActionType(),requiredEmber,false);
    }

    @Override
    public void endRequirementCheck() {
        requirementCheck = requiredEmber;
    }

    @Override
    public JEIComponent<Embers> provideJEIComponent() {
        return new JEIComponentEmber(this);
    }

    public class RestrictionEmber extends ComponentOutputRestrictor {
        public final float inserted;
        public final MachineComponent exactComponent;

        public RestrictionEmber(float inserted, MachineComponent exactComponent) {
            this.inserted = inserted;
            this.exactComponent = exactComponent;
        }
    }
}
