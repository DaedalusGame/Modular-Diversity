package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;
import mysticalmechanics.api.IMechUnit;
import mysticalmechanics.api.MysticalMechanicsAPI;

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
        double max = getRequiredLevelMax();
        double min = getRequiredLevelMin();

        IMechUnit unit = MysticalMechanicsAPI.IMPL.getDefaultUnit();

        if(max == min)
            return unit.format(min);
        else if(Double.isInfinite(max))
            return ">"+unit.format(min);
        else if(min <= 0)
            return "<"+unit.format(max);
        else
            return unit.format(min)+" - "+unit.format(max);
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
