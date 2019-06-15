package modulardiversity.jei;

import com.google.common.collect.Lists;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement.JEIComponent;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementPosition;
import modulardiversity.components.requirements.RequirementReservoir;
import modulardiversity.jei.ingredients.Position;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.renderer.RendererPosition;
import modulardiversity.jei.renderer.RendererReservoir;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class JEIComponentPosition extends JEIComponent<Position> {

    private final RequirementPosition requirement;

    public JEIComponentPosition(RequirementPosition requirement) {
        this.requirement = requirement;
    }

    @Override
    public Class<Position> getJEIRequirementClass() {
        return Position.class;
    }

    @Override
    public List<Position> getJEIIORequirements() {
        return Lists.newArrayList(new Position(requirement.xMin,requirement.xMax,requirement.yMin,requirement.yMax,requirement.zMin,requirement.zMax,requirement.distanceMin,requirement.distanceMax));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public RecipeLayoutPart<Position> getLayoutPart(Point point) {
        return new Layout(point);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onJEIHoverTooltip(int i, boolean b, Position weather, List<String> list) {
    }

    public static class Layout extends RecipeLayoutPart<Position> {
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
        public Class<Position> getLayoutTypeClass() {
            return Position.class;
        }

        @Override
        public IIngredientRenderer<Position> provideIngredientRenderer() {
            return new RendererPosition();
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
