package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementEmber;
import modulardiversity.components.requirements.RequirementMana;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.jei.ingredients.Mana;
import modulardiversity.jei.renderer.RendererEmber;
import modulardiversity.jei.renderer.RendererMana;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentMana extends ComponentRequirement.JEIComponent<Mana> {
    private final RequirementMana requirement;

    public JEIComponentMana(RequirementMana requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Mana> getJEIRequirementClass() {
        return Mana.class;
    }

    @Override
    public List<Mana> getJEIIORequirements() {
        return Lists.newArrayList(new Mana(requirement.requiredMana));
    }

    @Override
    public RecipeLayoutPart<Mana> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, Mana embers, List<String> list) {
    }

    public static class LayoutPart extends RecipeLayoutPart<Mana> {
        public LayoutPart(Point offset) {
            super(offset);
        }

        @Override
        public int getComponentWidth() {
            return 5;
        }

        @Override
        public int getComponentHeight() {
            return 63;
        }

        @Override
        public Class<Mana> getLayoutTypeClass() {
            return Mana.class;
        }

        @Override
        public IIngredientRenderer<Mana> provideIngredientRenderer() {
            return new RendererMana();
        }

        @Override
        public int getRendererPaddingX() {
            return 0;
        }

        @Override
        public int getRendererPaddingY() {
            return 0;
        }

        @Override
        public int getMaxHorizontalCount() {
            return 1;
        }

        @Override
        public int getComponentHorizontalGap() {
            return 4;
        }

        @Override
        public int getComponentVerticalGap() {
            return 4;
        }

        @Override
        public int getComponentHorizontalSortingOrder() {
            return 900;
        }

        @Override
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

//        @Override
//        public void drawForeground(Minecraft minecraft, Mana mana) {
//        }
    }
}
