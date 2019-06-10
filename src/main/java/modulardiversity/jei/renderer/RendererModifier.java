package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.ingredients.Modifier;
import modulardiversity.jei.ingredients.Reservoir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.List;

public class RendererModifier implements IIngredientRenderer<Modifier> {

    private void registerDrawables() {

    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Modifier ingredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Modifier ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(ingredient.getDisplayName());
    }
}