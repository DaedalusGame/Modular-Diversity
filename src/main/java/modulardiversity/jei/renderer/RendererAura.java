package modulardiversity.jei.renderer;

import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Aura;
import modulardiversity.jei.ingredients.Reservoir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererAura implements IIngredientRenderer<Aura> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Aura ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Aura ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();

        tooltip.add(ingredient.getDisplayName());
        if (ingredient.getVisMax() < Integer.MAX_VALUE || ingredient.getVisMin() > 0)
            tooltip.add(I18n.format(getVisKey(ingredient), ingredient.getVisMin(), ingredient.getVisMax()));
        if (ingredient.getFluxMax() < Integer.MAX_VALUE || ingredient.getFluxMin() > 0)
            tooltip.add(I18n.format(getFluxKey(ingredient), ingredient.getFluxMin(), ingredient.getFluxMax()));
        tooltip.add(I18n.format(getVisResultKey(ingredient), ingredient.getVis()));
        tooltip.add(I18n.format(getFluxResultKey(ingredient), ingredient.getFlux()));

        return tooltip;
    }

    private String getFluxKey(Aura ingredient) {
        if (ingredient.getFluxMin() <= 0)
            return "tooltip.flux.less";
        else if (ingredient.getFluxMax() >= Integer.MAX_VALUE)
            return "tooltip.flux.greater";
        else if (ingredient.getFluxMax() == ingredient.getFluxMin())
            return "tooltip.flux.exact";
        else
            return "tooltip.flux";
    }

    private String getVisKey(Aura ingredient) {
        if (ingredient.getVisMin() <= 0)
            return "tooltip.vis.less";
        else if (ingredient.getVisMax() >= Integer.MAX_VALUE)
            return "tooltip.vis.greater";
        else if (ingredient.getVisMax() == ingredient.getVisMin())
            return "tooltip.vis.exact";
        else
            return "tooltip.vis";
    }

    private String getVisResultKey(Aura ingredient) {
        if (ingredient.getVis() > 0)
            return "tooltip.vis.fill";
        else if (ingredient.getVis() < 0)
            return "tooltip.vis.deplete";
        else
            return "tooltip.vis.none";
    }

    private String getFluxResultKey(Aura ingredient) {
        if (ingredient.getFlux() > 0)
            return "tooltip.flux.fill";
        else if (ingredient.getFlux() < 0)
            return "tooltip.flux.deplete";
        else
            return "tooltip.flux.none";
    }
}