package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Embers implements IFakeIngredient {
    double consumedEmbers;

    public Embers(double consumedEmbers) {
        this.consumedEmbers = consumedEmbers;
    }

    public double getConsumedEmbers() {
        return consumedEmbers;
    }

    @Override
    public String getDisplayName() {
        return "Embers";
    }

    @Override
    public String getUniqueID() {
        return "embers";
    }
}
