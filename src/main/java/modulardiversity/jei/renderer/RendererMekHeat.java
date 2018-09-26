package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.MekHeat;
import modulardiversity.jei.ingredients.MekLaser;
import modulardiversity.util.HeatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class RendererMekHeat implements IIngredientRenderer<MekHeat> {
    private IDrawableStatic heat_bar;
    private IDrawableStatic heat_bar_fill;

    public RendererMekHeat() {
    }

    private void registerDrawables() {
        if (heat_bar == null)
            heat_bar = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 34, 63, 14, 14);
        if (heat_bar_fill == null)
            heat_bar_fill = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 20, 63, 14, 14);
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable MekHeat ingredient) {
        registerDrawables();

        if(ingredient == null)
            return;

        GlStateManager.enableAlpha();
        GlStateManager.color(1f,1f,1f,1f);
        heat_bar.draw(minecraft,xPosition,yPosition);
        long l = System.currentTimeMillis();
        double maxFill = 14;
        int currentFill = (int) ((l / 200D) % maxFill+1);
        if(ingredient.getTemperature() > 0)
            currentFill = (int) (maxFill - currentFill + 1);
        Color heatColor = HeatUtils.getHeatColor(MathHelper.clampedLerp(ingredient.getRequiredTemperatureMin(),Math.min(ingredient.getRequiredTemperatureMax(),100000),1-(currentFill / maxFill)));
        GlStateManager.color(heatColor.getRed()/255f,heatColor.getGreen()/255f,heatColor.getBlue()/255f,heatColor.getAlpha()/255f);
        heat_bar_fill.draw(minecraft,xPosition,yPosition,currentFill,0,0,0);
        GlStateManager.color(1f,1f,1f,1f);
        GlStateManager.disableAlpha();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, MekHeat ingredient, ITooltipFlag tooltipFlag) {
        DecimalFormat requirementFormat = new DecimalFormat("0.##");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setInfinity("∞");
        requirementFormat.setDecimalFormatSymbols(symbols);
        DecimalFormat tickFormat = new DecimalFormat("+0.##;-0.##");

        List<String> tooltip = Lists.newArrayList("Mekanism Heat");

        boolean omitMin = ingredient.getRequiredTemperatureMin() <= 0;
        boolean omitMax = ingredient.getRequiredTemperatureMax() >= Double.POSITIVE_INFINITY;
        if(!omitMin && !omitMax)
            tooltip.add(TextFormatting.LIGHT_PURPLE+"Requires between "+requirementFormat.format(ingredient.getRequiredTemperatureMin())+" and "+requirementFormat.format(ingredient.getRequiredTemperatureMax())+" heat");
        else if(!omitMax)
            tooltip.add(TextFormatting.LIGHT_PURPLE+"Requires less than "+requirementFormat.format(ingredient.getRequiredTemperatureMax())+" heat");
        else if(!omitMin)
            tooltip.add(TextFormatting.LIGHT_PURPLE+"Requires more than "+requirementFormat.format(ingredient.getRequiredTemperatureMin())+" heat");
        tooltip.add(TextFormatting.LIGHT_PURPLE+""+tickFormat.format(ingredient.getTemperature())+" heat/t");

        return tooltip;
    }
}
