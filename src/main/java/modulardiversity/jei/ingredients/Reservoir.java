package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Reservoir implements IFakeIngredient {
    private String name;
    private int fluidMin, fluidMax;
    private int residualMin, residualMax;
    private int amount;

    public Reservoir(String name, int fluidMin, int fluidMax, int residualMin, int residualMax, int amount) {
        this.name = name;
        this.fluidMin = fluidMin;
        this.fluidMax = fluidMax;
        this.residualMin = residualMin;
        this.residualMax = residualMax;
        this.amount = amount;
    }

    public String getName() {
        return name;
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

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public String getUniqueID() {
        return "reservoir";
    }
}
