package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Mechanical;
import modulardiversity.jei.ingredients.MysticalMechanics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.List;

public class RendererMysticalMechanics implements IIngredientRenderer<MysticalMechanics> {
    static IDrawable foreground_crank;

    public RendererMysticalMechanics() {
    }

    private void registerDrawables() {
        if (foreground_crank == null)
            foreground_crank = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 48, 63, 16, 16);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, MysticalMechanics ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Mechanical Power",ingredient.getDisplayName());
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable MysticalMechanics mechanical) {
        registerDrawables();
        if(mechanical == null)
            return;
        GlStateManager.enableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.pushMatrix();
        GlStateManager.translate(xPosition,yPosition,0);
        GlStateManager.translate(8,8,0);
        long l = System.currentTimeMillis();
        double speed = MathHelper.clampedLerp(mechanical.getRequiredLevelMin(),Math.min(1000000,mechanical.getRequiredLevelMax()),MathHelper.clamp(0.5+Math.sin(l/800.0),0,1)) / 20;
        mechanical.currentAngle += speed;
        GlStateManager.rotate((float) mechanical.currentAngle,0,0,1);
        GlStateManager.translate(-8,-8,0);
        foreground_crank.draw(minecraft,0,0);
        GlStateManager.popMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
    }
}
