package modulardiversity.jei.renderer;

import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Anchor;
import modulardiversity.jei.ingredients.Aura;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RendererAnchor implements IIngredientRenderer<Anchor> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Anchor ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Anchor ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<>();
        return tooltip;
    }
}