package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementMekLaser;
import modulardiversity.jei.ingredients.MekLaser;
import modulardiversity.jei.renderer.RendererMekLaser;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentMekLaser extends ComponentRequirement.JEIComponent<MekLaser> {
    private final RequirementMekLaser requirement;

    public JEIComponentMekLaser(RequirementMekLaser requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<MekLaser> getJEIRequirementClass() {
        return MekLaser.class;
    }

    @Override
    public List<MekLaser> getJEIIORequirements() {
        return Lists.newArrayList(new MekLaser(requirement.requiredEnergy));
    }

    @Override
    public RecipeLayoutPart<MekLaser> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, MekLaser mekLaser, List<String> list) {

    }

    public static class LayoutPart extends RecipeLayoutPart<MekLaser> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 14;
        }

        @Override
        public int getComponentHeight() {
            return 63;
        }

        @Override
        public Class<MekLaser> getLayoutTypeClass() {
            return MekLaser.class;
        }

        @Override
        public IIngredientRenderer<MekLaser> provideIngredientRenderer() {
            return new RendererMekLaser();
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
    }

}
