package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementMechanical;
import modulardiversity.components.requirements.RequirementMysticalMechanics;
import modulardiversity.jei.ingredients.Mechanical;
import modulardiversity.jei.ingredients.MysticalMechanics;
import modulardiversity.jei.renderer.RendererMechanical;
import modulardiversity.jei.renderer.RendererMysticalMechanics;
import mysticalmechanics.api.IMechUnit;
import mysticalmechanics.api.MysticalMechanicsAPI;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentMysticalMechanics extends ComponentRequirement.JEIComponent<MysticalMechanics> {
    private final RequirementMysticalMechanics requirement;

    public JEIComponentMysticalMechanics(RequirementMysticalMechanics requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<MysticalMechanics> getJEIRequirementClass() {
        return MysticalMechanics.class;
    }

    @Override
    public List<MysticalMechanics> getJEIIORequirements() {
        MysticalMechanics mysticalMechanics;
        if(requirement.getActionType() == MachineComponent.IOType.INPUT) {
            mysticalMechanics = new MysticalMechanics(requirement.getRequiredLevelMin(), requirement.getRequiredLevelMax());
        } else {
            mysticalMechanics = new MysticalMechanics(requirement.getLevelOutput());
        }
        return Lists.newArrayList(mysticalMechanics);
    }

    @Override
    public RecipeLayoutPart<MysticalMechanics> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int i, boolean b, MysticalMechanics mechanical, List<String> list) {

    }

    public static class LayoutPart extends RecipeLayoutPart<MysticalMechanics> {
        public LayoutPart(Point offset) {
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
        public Class<MysticalMechanics> getLayoutTypeClass() {
            return MysticalMechanics.class;
        }

        @Override
        public IIngredientRenderer<MysticalMechanics> provideIngredientRenderer() {
            return new RendererMysticalMechanics();
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
        @Deprecated
        public boolean canBeScaled() {
            return true;
        }

        @Override
        public void drawBackground(Minecraft minecraft) {
        }

//        @Override
//        public void drawForeground(Minecraft minecraft, Mechanical mechanical) {
//        }
    }
}
