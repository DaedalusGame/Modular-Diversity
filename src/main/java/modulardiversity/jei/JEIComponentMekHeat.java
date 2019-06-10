package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementMekHeat;
import modulardiversity.components.requirements.RequirementMekLaser;
import modulardiversity.jei.ingredients.MekHeat;
import modulardiversity.jei.ingredients.MekLaser;
import modulardiversity.jei.renderer.RendererMekHeat;
import modulardiversity.jei.renderer.RendererMekLaser;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentMekHeat extends ComponentRequirement.JEIComponent<MekHeat> {
    private final RequirementMekHeat requirement;

    public JEIComponentMekHeat(RequirementMekHeat requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<MekHeat> getJEIRequirementClass() {
        return MekHeat.class;
    }

    @Override
    public List<MekHeat> getJEIIORequirements() {
        return Lists.newArrayList(new MekHeat(requirement.getTemperature(),requirement.getTemperatureRequiredMin(),requirement.getTemperatureRequiredMax()));
    }

    @Override
    public RecipeLayoutPart<MekHeat> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, MekHeat mekLaser, List<String> list) {

    }

    public static class LayoutPart extends RecipeLayoutPart<MekHeat> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 14;
        }

        @Override
        public int getComponentHeight() {
            return 14;
        }

        @Override
        public Class<MekHeat> getLayoutTypeClass() {
            return MekHeat.class;
        }

        @Override
        public IIngredientRenderer<MekHeat> provideIngredientRenderer() {
            return new RendererMekHeat();
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
        @Deprecated
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }
    }

}
