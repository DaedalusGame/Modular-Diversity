package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Mechanical implements IFakeIngredient {
    int level;
    boolean crankAllowed;

    public Mechanical(int level, boolean crankAllowed) {
        this.level = level;
        this.crankAllowed = crankAllowed;
    }

    public int getLevel() {
        return level;
    }

    public boolean isCrankAllowed() {
        return crankAllowed;
    }

    @Override
    public String getDisplayName() {
        return "Mechanical Power";
    }

    @Override
    public String getUniqueID() {
        return "mechanical";
    }
}
