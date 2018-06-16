package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Laser implements IFakeIngredient {
    float requiredMicroMJ;

    public Laser(float requiredMicroMJ) {
        this.requiredMicroMJ = requiredMicroMJ;
    }

    public float getRequiredMicroMJ() {
        return requiredMicroMJ;
    }

    @Override
    public String getDisplayName() {
        return "Laser";
    }

    @Override
    public String getUniqueID() {
        return "laser";
    }
}
