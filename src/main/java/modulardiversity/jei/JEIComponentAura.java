package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementAura;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.jei.ingredients.Aura;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.renderer.RendererAura;
import modulardiversity.jei.renderer.RendererReservoir;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentAura extends JEIComponent<Aura> {

    private final RequirementAura requirement;

    public JEIComponentAura(RequirementAura requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Aura> getJEIRequirementClass() {
        return Aura.class;
    }

    @Override
    public List<Aura> getJEIIORequirements() {
        return Lists.newArrayList(new Aura(requirement.visMin,requirement.visMax,requirement.fluxMin,requirement.fluxMax,requirement.vis,requirement.flux));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Aura> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Aura weather, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Aura> {
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
        public Class<Aura> getLayoutTypeClass() {
            return Aura.class;
        }

        @Override
        public IIngredientRenderer<Aura> provideIngredientRenderer() {
            return new RendererAura();
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
