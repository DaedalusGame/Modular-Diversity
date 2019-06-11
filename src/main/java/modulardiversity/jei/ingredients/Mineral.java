package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Mineral implements IFakeIngredient {
    private String name;
    private int oreMin, oreMax;
    private int amount;

    public Mineral(String name, int oreMin, int oreMax, int amount) {
        this.name = name;
        this.oreMin = oreMin;
        this.oreMax = oreMax;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getOreMin() {
        return oreMin;
    }

    public int getOreMax() {
        return oreMax;
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
        return "mineral";
    }
}
