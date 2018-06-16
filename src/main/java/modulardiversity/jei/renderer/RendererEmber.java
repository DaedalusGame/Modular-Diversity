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
import teamroots.embers.gui.GuiCodex;
import teamroots.embers.util.EmberGenUtil;
import teamroots.embers.util.RenderUtil;

import javax.annotation.Nullable;
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

        ember_crystal.draw(minecraft,xPosition,yPosition);

        GlStateManager.disableDepth();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        int func = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
        float ref = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
        GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
        GuiCodex.drawTextGlowingAura(minecraft.fontRenderer, Double.toString(embers.getConsumedEmbers()), xPosition, yPosition);
        GlStateManager.alphaFunc(func, ref);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
    }
}
