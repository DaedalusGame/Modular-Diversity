package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;
import modulardiversity.util.DimensionNameUtil;
import modulardiversity.util.Misc;

import java.util.Collection;

public class DimensionIngredient implements IFakeIngredient {
    private Collection<Integer> dimensions;

    public DimensionIngredient(Collection<Integer> dimensions) {
        this.dimensions = dimensions;
    }

    public Collection<Integer> getBiomes(int index) {
        return dimensions;
    }

    @Override
    public String getDisplayName() {
        return dimensions.stream().map(DimensionNameUtil::getLocalizedName).collect(Misc.englishList());
    }

    @Override
    public String getUniqueID() {
        return "dimension";
    }
}
