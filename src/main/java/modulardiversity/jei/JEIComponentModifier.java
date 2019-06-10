package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementModifier;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.jei.ingredients.Modifier;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.renderer.RendererModifier;
import modulardiversity.jei.renderer.RendererReservoir;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentModifier extends JEIComponent<Modifier> {

    private final RequirementModifier requirement;

    public JEIComponentModifier(RequirementModifier requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Modifier> getJEIRequirementClass() {
        return Modifier.class;
    }

    @Override
    public List<Modifier> getJEIIORequirements() {
        return Lists.newArrayList(new Modifier(requirement.name,requirement.min,requirement.max));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Modifier> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Modifier ingredient, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Modifier> {
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
        public Class<Modifier> getLayoutTypeClass() {
            return Modifier.class;
        }

        @Override
        public IIngredientRenderer<Modifier> provideIngredientRenderer() {
            return new RendererModifier();
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
