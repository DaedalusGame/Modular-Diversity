package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class HotAir implements IFakeIngredient {
	int tempRequired;

    public HotAir(int tempRequired) {
        this.tempRequired = tempRequired;
    }

    public float getTempRequired() {
        return tempRequired;
    }

    @Override
    public String getDisplayName() {
        return "Air Temperature";
    }

    @Override
    public String getUniqueID() {
        return "hotair";
    }
}