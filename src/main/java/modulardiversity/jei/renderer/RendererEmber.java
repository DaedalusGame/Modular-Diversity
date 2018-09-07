package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Embers;
import modulardiversity.jei.ingredients.Mechanical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import org.lwjgl.opengl.GL11;
import teamroots.embers.api.EmbersAPI;
import teamroots.embers.gui.GuiCodex;
import teamroots.embers.util.EmberGenUtil;
import teamroots.embers.util.RenderUtil;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class RendererEmber implements IIngredientRenderer<Embers> {
    static IDrawable ember_crystal;

    public RendererEmber() {
    }

    private void registerDrawables() {
        if (ember_crystal == null)
            ember_crystal = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 0, 0, 12, 14);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Embers ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Ember");
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable Embers embers) {
        registerDrawables();

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        ember_crystal.draw(minecraft,xPosition+(40-16)/2,yPosition);
        DecimalFormat emberFormat = teamroots.embers.Embers.proxy.getDecimalFormat("embers.decimal_format.ember");
        double consumedEmbers = embers != null ? embers.getConsumedEmbers() : 0;
        String emberString = emberFormat.format(consumedEmbers);
        GuiCodex.drawTextGlowing(minecraft.fontRenderer, emberString, xPosition+(40-minecraft.fontRenderer.getStringWidth(emberString))/2, yPosition+(28-minecraft.fontRenderer.FONT_HEIGHT));
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }
}
