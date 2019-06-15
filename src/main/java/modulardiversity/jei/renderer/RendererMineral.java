package modulardiversity.jei.renderer;

import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Mineral;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererMineral implements IIngredientRenderer<Mineral> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Mineral ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Mineral ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();

        tooltip.add(ingredient.getDisplayName());
        //if(tooltipFlag.isAdvanced()) {
            if (ingredient.getOreMax() < Integer.MAX_VALUE || ingredient.getOreMin() > 0)
                tooltip.add(I18n.format(getAmountKey(ingredient),ingredient.getOreMin(),ingredient.getOreMax()));
            tooltip.add(I18n.format(getResultKey(ingredient),ingredient.getAmount()));
        //}

        return tooltip;
    }

    private String getAmountKey(Mineral ingredient) {
        if(ingredient.getOreMin() <= 0)
            return "tooltip.mineral.amount.less";
        else if(ingredient.getOreMax() >= Integer.MAX_VALUE)
            return "tooltip.mineral.amount.greater";
        else if(ingredient.getOreMax() == ingredient.getOreMin())
            return "tooltip.mineral.amount.exact";
        else
            return "tooltip.mineral.amount";
    }

    private String getResultKey(Mineral ingredient) {
        if(ingredient.getAmount() > 0)
            return "tooltip.mineral.result.fill";
        else if(ingredient.getAmount() < 0)
            return "tooltip.mineral.result.deplete";
        else
            return "tooltip.mineral.result.none";
    }
}