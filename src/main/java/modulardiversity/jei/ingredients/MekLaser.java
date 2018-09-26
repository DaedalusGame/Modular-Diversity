package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class MekLaser implements IFakeIngredient {
    double consumedEnergy;

    //LOL
    public MekLaser(double consumedEmbers) {
        this.consumedEnergy = consumedEmbers;
    }

    public double getConsumedEnergy() {
        return consumedEnergy;
    }

    @Override
    public String getDisplayName() {
        return "Laser Energy";
    }

    @Override
    public String getUniqueID() {
        return "meklaser";
    }
}
