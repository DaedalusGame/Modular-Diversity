package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementMineral;
import modulardiversity.jei.ingredients.Mineral;
import modulardiversity.jei.renderer.RendererMineral;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentMineral extends JEIComponent<Mineral> {

    private final RequirementMineral requirement;

    public JEIComponentMineral(RequirementMineral requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Mineral> getJEIRequirementClass() {
        return Mineral.class;
    }

    @Override
    public List<Mineral> getJEIIORequirements() {
        return Lists.newArrayList(new Mineral(requirement.name,requirement.oreMin,requirement.oreMax,requirement.amount));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Mineral> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Mineral weather, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Mineral> {
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
        public Class<Mineral> getLayoutTypeClass() {
            return Mineral.class;
        }

        @Override
        public IIngredientRenderer<Mineral> provideIngredientRenderer() {
            return new RendererMineral();
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
