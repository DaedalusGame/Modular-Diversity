package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementDaylight;
import modulardiversity.jei.ingredients.DaylightIngredient;
import modulardiversity.jei.renderer.DaylightRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentDaylight extends JEIComponent<DaylightIngredient> {

    private final RequirementDaylight requirement;

    public JEIComponentDaylight(RequirementDaylight requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<DaylightIngredient> getJEIRequirementClass() {
        return DaylightIngredient.class;
    }

    @Override
    public List<DaylightIngredient> getJEIIORequirements() {
        return Lists.newArrayList(new DaylightIngredient(requirement.getDaylight()));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<DaylightIngredient> getLayoutPart(Point point) {
        return new DaylightLayout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, DaylightIngredient biomeIngredient, List<String> list) {
    }

    public static class DaylightLayout extends RecipeLayoutPart<DaylightIngredient> {


        protected DaylightLayout(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 34;
        }

        @Override
        public int getComponentHeight() {
            return 18;
        }

        @Override
        public Class<DaylightIngredient> getLayoutTypeClass() {
            return DaylightIngredient.class;
        }

        @Override
        public IIngredientRenderer<DaylightIngredient> provideIngredientRenderer() {
            return new DaylightRenderer();
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
            return 1;
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
