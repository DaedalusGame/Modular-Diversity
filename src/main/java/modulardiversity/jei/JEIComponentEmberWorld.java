package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementEmberWorld;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.jei.ingredients.EmberWorld;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.renderer.RendererEmberWorld;
import modulardiversity.jei.renderer.RendererReservoir;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentEmberWorld extends JEIComponent<EmberWorld> {

    private final RequirementEmberWorld requirement;

    public JEIComponentEmberWorld(RequirementEmberWorld requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<EmberWorld> getJEIRequirementClass() {
        return EmberWorld.class;
    }

    @Override
    public List<EmberWorld> getJEIIORequirements() {
        return Lists.newArrayList(new EmberWorld(requirement.emberMin,requirement.emberMax,requirement.stabilityMin,requirement.stabilityMax));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<EmberWorld> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, EmberWorld weather, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<EmberWorld> {
        protected Layout(Point offset) {
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
        public Class<EmberWorld> getLayoutTypeClass() {
            return EmberWorld.class;
        }

        @Override
        public IIngredientRenderer<EmberWorld> provideIngredientRenderer() {
            return new RendererEmberWorld();
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
