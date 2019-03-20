package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType.Registry;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.components.MachineComponents;
import modulardiversity.jei.JEIComponentHotAir;
import modulardiversity.jei.ingredients.HotAir;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;

import java.util.List;

public class RequirementHotAir  extends RequirementConsumePerTick<HotAir, RequirementHotAir.ResourceToken> {
	public final int requiredTempMin;
    public final int requiredTempMax;
	public final int temp;

	public RequirementHotAir(IOType ioType, int requiredTempMin, int requiredTempMax, int temp) {
        super(Registry.getComponent("hotair"),ioType);
        this.requiredTempMin = requiredTempMin;
        this.requiredTempMax = requiredTempMax;
        this.temp = temp;
    }
	
	@Override
    public ComponentRequirement deepCopy() {
        return new RequirementHotAir(getActionType(), requiredTempMin, requiredTempMax, temp);
    }

    @Override
    public ComponentRequirement<HotAir> deepCopyModified(List<RecipeModifier> modifiers) {
        return  new RequirementHotAir(getActionType(),
                Misc.applyModifiers(modifiers,"hotair_min",getActionType(), requiredTempMin,false),
                Misc.applyModifiers(modifiers,"hotair_max",getActionType(), requiredTempMin,false),
                Misc.applyModifiers(modifiers,"hotair",getActionType(), temp,false)
        );
    }

    @Override
    public JEIComponent<HotAir> provideJEIComponent() {
        return new JEIComponentHotAir(this);
    }
	
	@Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredTempMin,requiredTempMax,temp);
    }
	
	@Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("hotair") &&
                component instanceof MachineComponents.HotAirHatch &&
                component.getIOType() == getActionType();
    }

	public static class ResourceToken implements IResourceToken {
        private int tempRequiredMin;
        private int tempRequiredMax;
        private int temp;
        private boolean requiredTempMet;

        public ResourceToken(int tempRequiredMin,int tempRequiredMax,int temp) {
            this.tempRequiredMin = tempRequiredMin;
            this.tempRequiredMax = tempRequiredMax;
            this.temp = temp;
        }

        public int getRequiredMinTemp() {
            return tempRequiredMin;
        }

        public int getRequiredMaxTemp() {
            return tempRequiredMax;
        }

        public int getTemp() {
            return temp;
        }
        
        public void setRequiredTempMet() {
        	requiredTempMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, IOType ioType, float durationMultiplier) {
            tempRequiredMin = Misc.applyModifiers(modifiers,"hotair_min",ioType, tempRequiredMin,false);
            tempRequiredMax = Misc.applyModifiers(modifiers,"hotair_max",ioType, tempRequiredMax,false);
            temp = Misc.applyModifiers(modifiers,"hotair",ioType, temp,false);
        }

        @Override
        public boolean isEmpty() {
            return requiredTempMet;
        }
    }
}
