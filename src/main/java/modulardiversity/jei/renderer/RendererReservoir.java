package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.ModularDiversity;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Reservoir;
import modulardiversity.jei.ingredients.Weather;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererReservoir implements IIngredientRenderer<Reservoir> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Reservoir ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Reservoir ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();

        tooltip.add(ingredient.getDisplayName());
        //if(tooltipFlag.isAdvanced()) {
            if (ingredient.getFluidMax() < Integer.MAX_VALUE || ingredient.getFluidMin() > 0)
                tooltip.add(I18n.format(getAmountKey(ingredient),ingredient.getFluidMin(),ingredient.getFluidMax()));
            if (ingredient.getResidualMax() < Integer.MAX_VALUE || ingredient.getResidualMin() > 0)
                tooltip.add(I18n.format(getResidualKey(ingredient),ingredient.getResidualMin(),ingredient.getResidualMax()));
            tooltip.add(I18n.format(getResultKey(ingredient),ingredient.getAmount()));
        //}

        return tooltip;
    }

    private String getResidualKey(Reservoir ingredient) {
        if(ingredient.getResidualMin() <= 0)
            return "tooltip.reservoir.residual.less";
        else if(ingredient.getResidualMax() >= Integer.MAX_VALUE)
            return "tooltip.reservoir.residual.greater";
        else if(ingredient.getResidualMax() == ingredient.getResidualMin())
            return "tooltip.reservoir.residual.exact";
        else
            return "tooltip.reservoir.residual";
    }

    private String getAmountKey(Reservoir ingredient) {
        if(ingredient.getFluidMin() <= 0)
            return "tooltip.reservoir.amount.less";
        else if(ingredient.getFluidMax() >= Integer.MAX_VALUE)
            return "tooltip.reservoir.amount.greater";
        else if(ingredient.getFluidMax() == ingredient.getFluidMin())
            return "tooltip.reservoir.amount.exact";
        else
            return "tooltip.reservoir.amount";
    }

    private String getResultKey(Reservoir ingredient) {
        if(ingredient.getAmount() > 0)
            return "tooltip.reservoir.result.fill";
        else if(ingredient.getAmount() < 0)
            return "tooltip.reservoir.result.deplete";
        else
            return "tooltip.reservoir.result.none";
    }
}