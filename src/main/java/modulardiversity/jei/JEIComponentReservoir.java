package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.components.requirements.RequirementWeather;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.ingredients.Weather;
import modulardiversity.jei.renderer.RendererReservoir;
import modulardiversity.jei.renderer.RendererWeather;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentReservoir extends JEIComponent<Reservoir> {

    private final RequirementReservoir requirement;

    public JEIComponentReservoir(RequirementReservoir requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Reservoir> getJEIRequirementClass() {
        return Reservoir.class;
    }

    @Override
    public List<Reservoir> getJEIIORequirements() {
        return Lists.newArrayList(new Reservoir(requirement.name,requirement.fluidMin,requirement.fluidMax,requirement.residualMin,requirement.residualMax,requirement.amount));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Reservoir> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Reservoir weather, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Reservoir> {
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
        public Class<Reservoir> getLayoutTypeClass() {
            return Reservoir.class;
        }

        @Override
        public IIngredientRenderer<Reservoir> provideIngredientRenderer() {
            return new RendererReservoir();
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
