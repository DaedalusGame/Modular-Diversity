package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType.Registry;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.machine.MachineComponent.IOType;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.requirements.RequirementMechanical.ResourceToken;
import modulardiversity.jei.JEIComponentHotAir;
import modulardiversity.jei.ingredients.HotAir;
import modulardiversity.util.IResourceToken;

public class RequirementHotAir  extends RequirementConsumePerTick<HotAir, RequirementHotAir.ResourceToken> {
	public final int requiredTemp;
	
	public RequirementHotAir(IOType ioType, int requiredTemp) {
        super(Registry.getComponent("hotair"),ioType);
        this.requiredTemp = requiredTemp;
    }
	
	@Override
    public ComponentRequirement deepCopy() {
        return new RequirementHotAir(getActionType(), requiredTemp);
    }
	
	@Override
    public JEIComponent<HotAir> provideJEIComponent() {
        return new JEIComponentHotAir(this);
    }
	
	@Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(requiredTemp);
    }
	
	@Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().getRegistryName().equals("hotair") &&
                component instanceof MachineComponents.HotAirHatch &&
                component.getIOType() == getActionType();
    }

	public static class ResourceToken implements IResourceToken {
        private int tempRequired;
        private boolean requiredTempMet;

        public ResourceToken(int tempRequired) {
            this.tempRequired = tempRequired;
        }

        public int getRequiredTemp() {
            return tempRequired;
        }
        
        public void setRequiredTempMet() {
        	requiredTempMet = true;
        }

        @Override
        public float getModifier() {
            return (float)tempRequired;
        }

        @Override
        public void setModifier(float modifier) {
        	tempRequired = (int) modifier;
        }

        @Override
        public boolean isEmpty() {
            return requiredTempMet;
        }
    }
}
