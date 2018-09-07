package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Mechanical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;
import java.util.List;

public class RendererMechanical implements IIngredientRenderer<Mechanical> {
    static IDrawable foreground_crank;
    static IDrawable foreground_wood;
    static IDrawable foreground_steel;

    public RendererMechanical() {
    }

    private void registerDrawables() {
        if (foreground_crank == null)
            foreground_crank = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 0, 14, 14, 14);
        if (foreground_wood == null)
            foreground_wood = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 0, 28, 14, 14);
        if (foreground_steel == null)
            foreground_steel = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 0, 42, 14, 14);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Mechanical ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Mechanical Power");
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable Mechanical mechanical) {
        registerDrawables();
        int level = mechanical != null ? mechanical.getLevel() : 0;
        boolean isCrankable = mechanical != null && mechanical.isCrankAllowed();
        GlStateManager.enableAlpha();
        if(level > 1) {
            foreground_steel.draw(minecraft, xPosition+1, yPosition+1);
            int textOffset = minecraft.fontRenderer.getStringWidth(Integer.toString(level)) / 2;
            minecraft.fontRenderer.drawString(Integer.toString(level),xPosition-textOffset+8,yPosition+8,0xFFFFFF,true);
        }
        else {
            if(isCrankable)
                foreground_crank.draw(minecraft, xPosition + 1, yPosition + 1);
            else
                foreground_wood.draw(minecraft, xPosition + 1, yPosition + 1);
        }
        GlStateManager.disableAlpha();
    }
}
