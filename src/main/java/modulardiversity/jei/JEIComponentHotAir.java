package modulardiversity.jei;

import com.google.common.collect.Lists;

import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementHotAir;
import modulardiversity.jei.ingredients.HotAir;
import modulardiversity.jei.renderer.RendererHotAir;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.List;

public class JEIComponentHotAir extends ComponentRequirement.JEIComponent<HotAir> {
	private final RequirementHotAir requirement;
	
	public JEIComponentHotAir(RequirementHotAir requirement) {
        this.requirement = requirement;
    }
	
	@Override
	public Class<HotAir> getJEIRequirementClass(){
		return HotAir.class;
	}
	
	@Override
	public List<HotAir> getJEIIORequirements(){
		return Lists.newArrayList(new HotAir(requirement.requiredTemp));
	}
	
	@Override
    public RecipeLayoutPart<HotAir> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }
	
	@Override
    public void onJEIHoverTooltip(int i, boolean b, HotAir hotair, List<String> list) {
    }

    public static class LayoutPart extends RecipeLayoutPart<HotAir> {
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
        public Class<HotAir> getLayoutTypeClass() {
            return HotAir.class;
        }

        @Override
        public IIngredientRenderer<HotAir> provideIngredientRenderer() {
            return new RendererHotAir();
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
//        public void drawForeground(Minecraft minecraft, HotAir hotair) {
//        }
    }

}
