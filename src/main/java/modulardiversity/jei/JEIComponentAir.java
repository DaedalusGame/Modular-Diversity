package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementAir;
import modulardiversity.jei.ingredients.Air;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentAir extends ComponentRequirement.JEIComponent<Air> {
    private final RequirementAir requirement;

    public JEIComponentAir(RequirementAir requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Air> getJEIRequirementClass() {
        return Air.class;
    }

    @Override
    public List<Air> getJEIIORequirements() {
        return Lists.newArrayList(new Air(requirement.pressureRequired,requirement.volumeConsumed));
    }

    @Override
    public RecipeLayoutPart<Air> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Air air, List<String> list) {

    }

    public static class LayoutPart extends RecipeLayoutPart<Air> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 16;
        }

        @Override
        public int getComponentHeight() {
            return 16;
        }

        @Override
        public Class<Air> getLayoutTypeClass() {
            return Air.class;
        }

        @Override
        public IIngredientRenderer<Air> provideIngredientRenderer() {
            return new FakeIngredientRenderer<>();
        }

        @Override
        public int getRendererPaddingX() {
            return 0;
        }

        @Override
        public int getRendererPaddingY() {
            return 0;
        }

        @Override
        public int getMaxHorizontalCount() {
            return 1;
        }

        @Override
        public int getComponentHorizontalGap() {
            return 4;
        }

        @Override
        public int getComponentVerticalGap() {
            return 4;
        }

        @Override
        public int getComponentHorizontalSortingOrder() {
            return 900;
        }

        @Override
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

        @Override
        public void drawForeground(Minecraft minecraft, Air air) {
        }
    }
}
