package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementDaylight;
import modulardiversity.jei.ingredients.Daylight;
import modulardiversity.jei.renderer.RendererDaylight;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentDaylight extends JEIComponent<Daylight> {

    private final RequirementDaylight requirement;

    public JEIComponentDaylight(RequirementDaylight requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Daylight> getJEIRequirementClass() {
        return Daylight.class;
    }

    @Override
    public List<Daylight> getJEIIORequirements() {
        return Lists.newArrayList(new Daylight(requirement.timeMin,requirement.timeMax,requirement.timeModulo,requirement.timeLocal));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Daylight> getLayoutPart(Point point) {
        return new DaylightLayout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Daylight biomeIngredient, List<String> list) {
    }

    public static class DaylightLayout extends RecipeLayoutPart<Daylight> {


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
        public Class<Daylight> getLayoutTypeClass() {
            return Daylight.class;
        }

        @Override
        public IIngredientRenderer<Daylight> provideIngredientRenderer() {
            return new RendererDaylight();
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
        @Deprecated
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

    }
}
