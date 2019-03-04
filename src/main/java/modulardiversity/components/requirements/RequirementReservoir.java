package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.util.IResourceToken;

public class RequirementReservoir extends RequirementConsumeOnce<Reservoir, RequirementReservoir.ResourceToken> {
    private int fluidMin, fluidMax;
    private int residualMin, residualMax;
    private int amount;

    public RequirementReservoir(MachineComponent.IOType actionType, int fluidMin, int fluidMax, int residualMin, int residualMax, int amount) {
        super(ComponentType.Registry.getComponent("reservoir"), actionType);
        this.fluidMin = fluidMin;
        this.fluidMax = fluidMax;
        this.residualMin = residualMin;
        this.residualMax = residualMax;
        this.amount = amount;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return false;
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken(fluidMin,fluidMax,residualMin,residualMax,amount);
    }

    @Override
    public ComponentRequirement<Reservoir> deepCopy() {
        return new RequirementReservoir(getActionType(),fluidMin,fluidMax,residualMin,residualMax,amount);
    }

    @Override
    public JEIComponent<Reservoir> provideJEIComponent() {
        return null;
    }

    public static class ResourceToken implements IResourceToken
    {
        private int fluidMin, fluidMax;
        private int residualMin, residualMax;
        private int amount;

        public ResourceToken(int fluidMin, int fluidMax, int residualMin, int residualMax, int amount) {
            this.fluidMin = fluidMin;
            this.fluidMax = fluidMax;
            this.residualMin = residualMin;
            this.residualMax = residualMax;
            this.amount = amount;
        }

        public int getFluidMin() {
            return fluidMin;
        }

        public int getFluidMax() {
            return fluidMax;
        }

        public int getResidualMin() {
            return residualMin;
        }

        public int getResidualMax() {
            return residualMax;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public float getModifier() {
            return (float)amount;
        }

        @Override
        public void setModifier(float modifier) {
            amount = (int)modifier;
        }

        @Override
        public boolean isEmpty() {
            return amount <= 0;
        }
    }
}
