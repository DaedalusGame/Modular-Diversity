package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Anchor implements IFakeIngredient {
    public Anchor() {
    }

    @Override
    public String getDisplayName() {
        return "Anchor";
    }

    @Override
    public String getUniqueID() {
        return "anchor";
    }
}
