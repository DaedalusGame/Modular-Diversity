package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Mana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import org.lwjgl.opengl.GL11;
import teamroots.embers.gui.GuiCodex;

import javax.annotation.Nullable;
import java.util.List;

public class RendererMana implements IIngredientRenderer<Mana> {
    public RendererMana() {
    }

    private void registerDrawables() {

    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Mana ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Ember");
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable Mana mana) {
        registerDrawables();

        GlStateManager.disableDepth();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        int func = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
        float ref = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
        GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
        GuiCodex.drawTextGlowingAura(minecraft.fontRenderer, Double.toString(mana.getConsumedMana()), xPosition, yPosition);
        GlStateManager.alphaFunc(func, ref);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
    }
}
