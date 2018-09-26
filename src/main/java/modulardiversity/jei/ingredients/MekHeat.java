package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class MekHeat implements IFakeIngredient {
    double temperature;
    double requiredTemperatureMin;
    double requiredTemperatureMax;

    public MekHeat(double temperature, double requiredTemperatureMin, double requiredTemperatureMax) {
        this.temperature = temperature;
        this.requiredTemperatureMin = requiredTemperatureMin;
        this.requiredTemperatureMax = requiredTemperatureMax;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getRequiredTemperatureMin() {
        return requiredTemperatureMin;
    }

    public double getRequiredTemperatureMax() {
        return requiredTemperatureMax;
    }

    @Override
    public String getDisplayName() {
        return "Heat";
    }

    @Override
    public String getUniqueID() {
        return "mekheat";
    }
}