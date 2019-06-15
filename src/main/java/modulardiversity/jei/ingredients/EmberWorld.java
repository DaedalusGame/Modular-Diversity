package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class EmberWorld implements IFakeIngredient {
    private float emberMin, emberMax;
    private float stabilityMin, stabilityMax;

    public EmberWorld(float emberMin, float emberMax, float stabilityMin, float stabilityMax) {
        this.emberMin = emberMin;
        this.emberMax = emberMax;
        this.stabilityMin = stabilityMin;
        this.stabilityMax = stabilityMax;
    }

    public float getEmberMin() {
        return emberMin;
    }

    public float getEmberMax() {
        return emberMax;
    }

    public float getStabilityMin() {
        return stabilityMin;
    }

    public float getStabilityMax() {
        return stabilityMax;
    }

    @Override
    public String getDisplayName() {
        return "Mantle";
    }

    @Override
    public String getUniqueID() {
        return "mantle";
    }
}
