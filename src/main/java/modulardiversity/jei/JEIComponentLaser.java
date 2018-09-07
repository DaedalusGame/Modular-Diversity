package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementLaser;
import modulardiversity.jei.ingredients.Laser;
import modulardiversity.jei.renderer.RendererLaser;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentLaser extends ComponentRequirement.JEIComponent<Laser> {
    private final RequirementLaser requirement;

    public JEIComponentLaser(RequirementLaser requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Laser> getJEIRequirementClass() {
        return Laser.class;
    }

    @Override
    public List<Laser> getJEIIORequirements() {
        return Lists.newArrayList(new Laser(requirement.requiredMicroMJ));
    }

    @Override
    public RecipeLayoutPart<Laser> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Laser laser, List<String> list) {
    }

    public static class LayoutPart extends RecipeLayoutPart<Laser> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 6;
        }

        @Override
        public int getComponentHeight() {
            return 63;
        }

        @Override
        public Class<Laser> getLayoutTypeClass() {
            return Laser.class;
        }

        @Override
        public IIngredientRenderer<Laser> provideIngredientRenderer() {
            return new RendererLaser();
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
        public void drawForeground(Minecraft minecraft, Laser laser) {
        }
    }
}
