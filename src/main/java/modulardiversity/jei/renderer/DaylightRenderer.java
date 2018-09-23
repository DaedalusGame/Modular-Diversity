package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.DaylightIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class DaylightRenderer implements IIngredientRenderer<DaylightIngredient> {
    ResourceLocation clock1Resource = new ResourceLocation("minecraft", "textures/items/clock_00.png");
    static IDrawable clock1;
    ResourceLocation clock2Resource = new ResourceLocation("minecraft", "textures/items/clock_00.png");
    static IDrawable clock2;
//    static IDrawable arrow;

    private void registerDrawables() {
        clock1 = JEIHelpers.GUI_HELPER.createDrawable(clock1Resource, 0, 0, 16, 16, 16, 16);
//        if (arrow == null)
//            arrow = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation("modularbiometrics", "textures/gui/arrow.png"), 0, 0, 16, 16, 16, 16);
        clock2 = JEIHelpers.GUI_HELPER.createDrawable(clock2Resource, 0, 0, 16, 16, 16, 16);
    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable DaylightIngredient daylightIngredient) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        clock1Resource = new ResourceLocation("minecraft", "textures/items/clock_" + daylightIngredient.getClockCfg(0) + ".png");
        clock2Resource = new ResourceLocation("minecraft", "textures/items/clock_" + daylightIngredient.getClockCfg(1) + ".png");

        registerDrawables();

        clock1.draw(minecraft, i , i1);
//        arrow.draw(minecraft, i+16, i1);
        clock2.draw(minecraft, i+16, i1);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, DaylightIngredient ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(ingredient.getDisplayName());
    }
}