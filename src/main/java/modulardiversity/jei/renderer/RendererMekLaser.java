package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.MekLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import scala.collection.LinearSeq;

import javax.annotation.Nullable;
import java.util.List;

public class RendererMekLaser implements IIngredientRenderer<MekLaser> {
    private IDrawableStatic energy_bar;
    private IDrawableAnimated energy_bar_fill;
    private IDrawableStatic energy_bar_gauges;

    public RendererMekLaser() {
    }

    private void registerDrawables() {
        if (energy_bar == null)
            energy_bar = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 46, 0, 14, 63);
        if (energy_bar_fill == null)
            energy_bar_fill = JEIHelpers.GUI_HELPER.createAnimatedDrawable(JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 60, 0, 12, 61), 60, IDrawableAnimated.StartDirection.BOTTOM, false);
        if (energy_bar_gauges == null)
            energy_bar_gauges = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 72, 0, 12, 61);
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable MekLaser ingredient) {
        registerDrawables();

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableColorLogic();
        energy_bar.draw(minecraft,xPosition,yPosition);
        energy_bar_fill.draw(minecraft,xPosition+1,yPosition+1);
        energy_bar_gauges.draw(minecraft,xPosition+1,yPosition+1);
        GlStateManager.disableColorMaterial();
        GlStateManager.disableColorLogic();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public void renderEnergyBar() {

    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, MekLaser ingredient, ITooltipFlag tooltipFlag) {
        double energy = ingredient.getConsumedEnergy()/1000;
        String e = (energy < 1000) ? Double.toString(energy) + "kJ" : (energy < 1000000) ? Double.toString(energy/1000) + "MJ" : Double.toString(energy/1000000) + "GJ";
        return Lists.newArrayList("Mekanism Laser Energy Required: " + e);
    }
}
