package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.jei.ingredients.Mechanical;
import modulardiversity.jei.renderer.RendererEmber;
import modulardiversity.jei.renderer.RendererMechanical;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentEmber extends ComponentRequirement.JEIComponent<Embers> {
    private final RequirementEmber requirement;

    public JEIComponentEmber(RequirementEmber requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Embers> getJEIRequirementClass() {
        return Embers.class;
    }

    @Override
    public List<Embers> getJEIIORequirements() {
        return Lists.newArrayList(new Embers(requirement.requiredEmber));
    }

    @Override
    public RecipeLayoutPart<Embers> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Embers embers, List<String> list) {
    }

    public static class LayoutPart extends RecipeLayoutPart<Embers> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 40;
        }

        @Override
        public int getComponentHeight() {
            return 28;
        }

        @Override
        public Class<Embers> getLayoutTypeClass() {
            return Embers.class;
        }

        @Override
        public IIngredientRenderer<Embers> provideIngredientRenderer() {
            return new RendererEmber();
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
//        public void drawForeground(Minecraft minecraft, Embers mechanical) {
//        }
    }
}
