package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Mana implements IFakeIngredient {
    float consumedMana;

    public Mana(float consumedEmbers) {
        this.consumedMana = consumedEmbers;
    }

    public float getConsumedEmbers() {
        return consumedMana;
    }

    @Override
    public String getDisplayName() {
        return "Mana";
    }

    @Override
    public String getUniqueID() {
        return "mana";
    }
}
