package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Laser implements IFakeIngredient {
    long requiredMicroMJ;

    public Laser(long requiredMicroMJ) {
        this.requiredMicroMJ = requiredMicroMJ;
    }

    public long getRequiredMicroMJ() {
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
