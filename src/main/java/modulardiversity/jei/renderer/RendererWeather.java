package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.ModularDiversity;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Weather;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class RendererWeather implements IIngredientRenderer<Weather> {
    IDrawable rain;
    IDrawable snow;
    IDrawable thunder;
    IDrawable clear;

    private void registerDrawables() {
        if (rain == null)
            rain = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation(ModularDiversity.MODID, "textures/blocks/overlay_rain.png"), 0, 0, 16, 16, 16,16);
        if (snow == null)
            snow = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation(ModularDiversity.MODID, "textures/blocks/overlay_rain.png"), 0, 0, 16, 16, 16,16);
        if (thunder == null)
            thunder = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation(ModularDiversity.MODID, "textures/blocks/overlay_thunder.png"), 0, 0, 16, 16, 16,16);
        if (clear == null)
            clear = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation(ModularDiversity.MODID, "textures/blocks/overlay_sunny.png"), 0, 0, 16, 16, 16,16);
    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable Weather weather) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        registerDrawables();

        if(weather != null)
        switch (weather.getWeather()) {
            case CLEAR:
                clear.draw(minecraft,i,i1); break;
            case RAIN:
                rain.draw(minecraft,i,i1);break;
            case STORM:
                thunder.draw(minecraft,i,i1);break;
            case SNOW:
                snow.draw(minecraft,i,i1);break;
        }

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Weather ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList(ingredient.getDisplayName());
    }
}