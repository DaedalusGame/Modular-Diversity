package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Modifier implements IFakeIngredient {
    String name;
    float min;
    float max;

    public Modifier(String name, float min, float max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    @Override
    public String getDisplayName() {
        return "Modifier";
    }

    @Override
    public String getUniqueID() {
        return "modifier";
    }
}
