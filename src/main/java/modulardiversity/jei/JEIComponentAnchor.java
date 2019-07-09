package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementAnchor;
import modulardiversity.components.requirements.RequirementAura;
import modulardiversity.jei.ingredients.Anchor;
import modulardiversity.jei.ingredients.Aura;
import modulardiversity.jei.renderer.RendererAnchor;
import modulardiversity.jei.renderer.RendererAura;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentAnchor extends JEIComponent<Anchor> {
    private final RequirementAnchor requirement;

    public JEIComponentAnchor(RequirementAnchor requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Anchor> getJEIRequirementClass() {
        return Anchor.class;
    }

    @Override
    public List<Anchor> getJEIIORequirements() {
        return Lists.newArrayList(new Anchor());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Anchor> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Anchor anchor, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Anchor> {
        protected Layout(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 0;
        }

        @Override
        public int getComponentHeight() {
            return 0;
        }

        @Override
        public Class<Anchor> getLayoutTypeClass() {
            return Anchor.class;
        }

        @Override
        public IIngredientRenderer<Anchor> provideIngredientRenderer() {
            return new RendererAnchor();
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
