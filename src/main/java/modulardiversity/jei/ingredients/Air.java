package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Air implements IFakeIngredient {
    float pressureRequired;
    int volumeConsumed;

    public Air(float pressureRequired, int volumeConsumed) {
        this.pressureRequired = pressureRequired;
        this.volumeConsumed = volumeConsumed;
    }

    public float getPressureRequired() {
        return pressureRequired;
    }

    public int getVolumeConsumed() {
        return volumeConsumed;
    }

    @Override
    public String getDisplayName() {
        return "Air Pressure";
    }

    @Override
    public String getUniqueID() {
        return "air";
    }
}
