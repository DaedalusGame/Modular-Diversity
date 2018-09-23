package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.ModIntegrationJEI;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.jei.ingredients.Mechanical;
import modulardiversity.jei.renderer.RendererMechanical;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentMechanical extends ComponentRequirement.JEIComponent<Mechanical> {
    private final RequirementMechanical requirement;

    public JEIComponentMechanical(RequirementMechanical requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Mechanical> getJEIRequirementClass() {
        return Mechanical.class;
    }

    @Override
    public List<Mechanical> getJEIIORequirements() {
        return Lists.newArrayList(new Mechanical(requirement.requiredLevel, requirement.isCrankAllowed));
    }

    @Override
    public RecipeLayoutPart<Mechanical> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Mechanical mechanical, List<String> list) {
    }

    public static class LayoutPart extends RecipeLayoutPart<Mechanical> {
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
        public Class<Mechanical> getLayoutTypeClass() {
            return Mechanical.class;
        }

        @Override
        public IIngredientRenderer<Mechanical> provideIngredientRenderer() {
            return new RendererMechanical();
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

//        @Override
//        public void drawForeground(Minecraft minecraft, Mechanical mechanical) {
//        }
    }
}
