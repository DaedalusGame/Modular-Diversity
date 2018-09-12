package modulardiversity.jei.renderer;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.HotAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.MathHelper;

public class RendererHotAir implements IIngredientRenderer<HotAir> {
    private IDrawableStatic hot_air_empty;
    private IDrawableStatic hot_air_fill;

    public RendererHotAir() {
    }

    private void registerDrawables() {
        if (hot_air_empty == null)
        	hot_air_empty = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 0, 73, 10, 15);
        if (hot_air_fill == null)
        	hot_air_fill = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 10, 73, 10, 15);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, HotAir ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList((int)ingredient.getTempRequired() + "°C Hot Air");
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable HotAir hotair) {
        registerDrawables();

        GlStateManager.enableAlpha();
        Color color = new Color(Color.HSBtoRGB(0.18F, 0.18F, 0.50F));
        int airTemp = (int) (hotair != null ? hotair.getTempRequired() : 0);
        renderFullOfHotAir(minecraft, xPosition, yPosition,color.getRGB(), (float)(Math.sin(System.currentTimeMillis() / 800D)+1)/8.0F + 0.75F, airTemp, 400);
        GlStateManager.disableAlpha();
    }

    public void renderFullOfHotAir(Minecraft minecraft, int x, int y, int color, float alpha, int airTemp, int maxTemp) {
    	GlStateManager.color(1F, 1F, 1F, alpha);
        hot_air_empty.draw(minecraft,x,y);
        int tempPercent = Math.max(0, (int) ((double) airTemp / (double) maxTemp*12));
        hot_air_fill.draw(minecraft,x,y);

        Color color_ = new Color(color);
        GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
        hot_air_fill.draw(minecraft,x,y, 0, MathHelper.clamp(tempPercent, 2, 16), 0, 0);
        GL11.glColor4ub((byte) 255, (byte) 255, (byte) 255, (byte) 255);
        
        //GuiCodex.drawTextGlowingAura(minecraft.fontRenderer, airTemp+"°", x, y+16);
    }
}
