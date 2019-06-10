package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementDimension;
import modulardiversity.jei.ingredients.DimensionIngredient;
import modulardiversity.jei.renderer.RendererDimension;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentDimension extends JEIComponent<DimensionIngredient> {

    private final RequirementDimension requirement;

    public JEIComponentDimension(RequirementDimension requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<DimensionIngredient> getJEIRequirementClass() {
        return DimensionIngredient.class;
    }

    @Override
    public List<DimensionIngredient> getJEIIORequirements() {
        return Lists.newArrayList(new DimensionIngredient(requirement.getDimensions()));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<DimensionIngredient> getLayoutPart(Point point) {
        return new BiomeLayout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, DimensionIngredient biomeIngredient, List<String> list) {
    }

    public static class BiomeLayout extends RecipeLayoutPart<DimensionIngredient> {
        protected BiomeLayout(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 18;
        }

        @Override
        public int getComponentHeight() {
            return 18;
        }

        @Override
        public Class<DimensionIngredient> getLayoutTypeClass() {
            return DimensionIngredient.class;
        }

        @Override
        public IIngredientRenderer<DimensionIngredient> provideIngredientRenderer() {
            return new RendererDimension();
        }

        @Override
        public int getRendererPaddingX() {
            return 1;
        }

        @Override
        public int getRendererPaddingY() {
            return 1;
        }

        @Override
        public int getMaxHorizontalCount() {
            return 3;
        }

        @Override
        public int getComponentHorizontalGap() {
            return 0;
        }

        @Override
        public int getComponentVerticalGap() {
            return 0;
        }

        @Override
        public int getComponentHorizontalSortingOrder() {
            return 10;
        }

        @Override
        @Deprecated
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

    }
}
