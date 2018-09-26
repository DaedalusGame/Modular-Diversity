package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class MysticalMechanics implements IFakeIngredient {
    private double requiredLevelMin;
    private double requiredLevelMax;
    private double outputLevel;
    private EnumType type;

    public double currentAngle;

    public MysticalMechanics(double requiredLevelMin, double requiredLevelMax) {
        this.requiredLevelMin = requiredLevelMin;
        this.requiredLevelMax = requiredLevelMax;
        this.type = EnumType.INPUT;
    }

    public MysticalMechanics(double outputLevel) {
        this.outputLevel = outputLevel;
        this.type = EnumType.OUTPUT;
    }

    public double getRequiredLevelMin() {
        return type == EnumType.INPUT ? requiredLevelMin : outputLevel;
    }

    public double getRequiredLevelMax() {
        return type == EnumType.INPUT ? requiredLevelMax : outputLevel;
    }

    @Override
    public String getDisplayName() {
        return "Mechanical Power";
    }

    @Override
    public String getUniqueID() {
        return "mysticalmechanics";
    }

    public enum EnumType {
        INPUT,
        OUTPUT
    }
}
