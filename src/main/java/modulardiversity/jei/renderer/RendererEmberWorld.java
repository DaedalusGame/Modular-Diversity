package modulardiversity.jei.renderer;

import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.EmberWorld;
import modulardiversity.jei.ingredients.Reservoir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererEmberWorld implements IIngredientRenderer<EmberWorld> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable EmberWorld ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, EmberWorld ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();

        tooltip.add(ingredient.getDisplayName());
        //if(tooltipFlag.isAdvanced()) {
            if (ingredient.getEmberMax() < Integer.MAX_VALUE || ingredient.getEmberMin() > 0)
                tooltip.add(I18n.format(getAmountKey(ingredient),ingredient.getEmberMin(),ingredient.getEmberMax()));
            if (ingredient.getStabilityMax() < Integer.MAX_VALUE || ingredient.getStabilityMin() > 0)
                tooltip.add(I18n.format(getResidualKey(ingredient),ingredient.getStabilityMin(),ingredient.getStabilityMax()));
        //}

        return tooltip;
    }

    private String getResidualKey(EmberWorld ingredient) {
        if(ingredient.getStabilityMin() <= 0)
            return "tooltip.ember_stability.less";
        else if(ingredient.getStabilityMax() >= Integer.MAX_VALUE)
            return "tooltip.ember_stability.greater";
        else if(ingredient.getStabilityMax() == ingredient.getStabilityMin())
            return "tooltip.ember_stability.exact";
        else
            return "tooltip.ember_stability";
    }

    private String getAmountKey(EmberWorld ingredient) {
        if(ingredient.getEmberMin() <= 0)
            return "tooltip.ember.less";
        else if(ingredient.getEmberMax() >= Integer.MAX_VALUE)
            return "tooltip.ember.greater";
        else if(ingredient.getEmberMax() == ingredient.getEmberMin())
            return "tooltip.ember.exact";
        else
            return "tooltip.ember";
    }
}