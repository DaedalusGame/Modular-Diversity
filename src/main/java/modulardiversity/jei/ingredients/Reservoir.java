package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Reservoir implements IFakeIngredient {
    private int fluidMin, fluidMax;
    private int residualMin, residualMax;
    private int amount;

    @Override
    public String getDisplayName() {
        return "Underground Reservoir";
    }

    @Override
    public String getUniqueID() {
        return "reservoir";
    }
}
