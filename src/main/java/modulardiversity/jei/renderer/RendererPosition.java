package modulardiversity.jei.renderer;

import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererPosition implements IIngredientRenderer<Position> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Position ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Position ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();
        tooltip.add(ingredient.getDisplayName());
        if(ingredient.getxMin() != 0 || ingredient.getxMax() != 0)
        addCoordinate(tooltip, ingredient.getxMin(), ingredient.getxMax(), "x");
        if(ingredient.getyMin() != 0 || ingredient.getyMax() != 0)
        addCoordinate(tooltip, ingredient.getyMin(), ingredient.getyMax(), "y");
        if(ingredient.getzMin() != 0 || ingredient.getzMax() != 0)
        addCoordinate(tooltip, ingredient.getzMin(), ingredient.getzMax(), "z");
        if(ingredient.getDistanceMax() > 0)
            addDistance(tooltip, ingredient.getDistanceMin(), ingredient.getDistanceMax());
        ingredient.getAnchor().addTooltip(tooltip);

        return tooltip;
    }

    protected void addCoordinate(List<String> tooltip, float min, float max, String key) {
        if(!Float.isInfinite(min) || !Float.isInfinite(max)) {
            tooltip.add(I18n.format(getKey(min, max, key), min, max));
        }
    }

    protected void addDistance(List<String> tooltip, float min, float max) {
        if(min > 0 || !Float.isInfinite(max)) {
            tooltip.add(I18n.format(getDistanceKey(min, max), min, max));
        }
    }

    private String getKey(float min, float max, String key) {
        if(Float.isInfinite(min))
            return "tooltip."+key+".less";
        else if(Float.isInfinite(max))
            return "tooltip."+key+".greater";
        else if(max == min)
            return "tooltip."+key+".exact";
        else
            return "tooltip."+key;
    }

    private String getDistanceKey(float min, float max) {
        if(min <= 0)
            return "tooltip.distance.less";
        else if(Float.isInfinite(max))
            return "tooltip.distance.greater";
        else if(max == min)
            return "tooltip.distance.exact";
        else
            return "tooltip.distance";
    }
}