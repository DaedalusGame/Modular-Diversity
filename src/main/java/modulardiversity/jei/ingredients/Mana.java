package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Mana implements IFakeIngredient {
    int consumedMana;

    public Mana(int consumedEmbers) {
        this.consumedMana = consumedEmbers;
    }

    public int getConsumedMana() {
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
