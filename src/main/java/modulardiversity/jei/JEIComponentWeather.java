package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementWeather;
import modulardiversity.jei.ingredients.Weather;
import modulardiversity.jei.renderer.RendererWeather;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentWeather extends JEIComponent<Weather> {

    private final RequirementWeather requirement;

    public JEIComponentWeather(RequirementWeather requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Weather> getJEIRequirementClass() {
        return Weather.class;
    }

    @Override
    public List<Weather> getJEIIORequirements() {
        return Lists.newArrayList(new Weather(requirement.weather));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Weather> getLayoutPart(Point point) {
        return new BiomeLayout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Weather weather, List<String> list) {
    }

    public static class BiomeLayout extends RecipeLayoutPart<Weather> {
        protected BiomeLayout(Point offset) {
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
        public Class<Weather> getLayoutTypeClass() {
            return Weather.class;
        }

        @Override
        public IIngredientRenderer<Weather> provideIngredientRenderer() {
            return new RendererWeather();
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
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

    }
}
