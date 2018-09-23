package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementBiome;
import modulardiversity.jei.ingredients.BiomeIngredient;
import modulardiversity.jei.renderer.BiomeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentBiome extends JEIComponent<BiomeIngredient> {

    private final RequirementBiome requirement;

    public JEIComponentBiome(RequirementBiome requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<BiomeIngredient> getJEIRequirementClass() {
        return BiomeIngredient.class;
    }

    @Override
    public List<BiomeIngredient> getJEIIORequirements() {
        return Lists.newArrayList(new BiomeIngredient(requirement.getBiomes()));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<BiomeIngredient> getLayoutPart(Point point) {
        return new BiomeLayout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, BiomeIngredient biomeIngredient, List<String> list) {
    }

    public static class BiomeLayout extends RecipeLayoutPart<BiomeIngredient> {
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
        public Class<BiomeIngredient> getLayoutTypeClass() {
            return BiomeIngredient.class;
        }

        @Override
        public IIngredientRenderer<BiomeIngredient> provideIngredientRenderer() {
            return new BiomeRenderer();
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
