package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.components.requirements.RequirementLaser;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.Laser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.List;

public class RendererLaser implements IIngredientRenderer<Laser> {
    private IDrawableStatic laser_bar;
    private IDrawableStatic laser_bar_fill;

    public RendererLaser() {
    }

    private void registerDrawables() {
        if (laser_bar == null)
            laser_bar = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 36, 0, 6, 63);
        if (laser_bar_fill == null)
            laser_bar_fill = JEIHelpers.GUI_HELPER.createDrawable(JEIHelpers.TEXTURE, 42, 0, 4, 61);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Laser ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Laser", TextFormatting.LIGHT_PURPLE+"Requires "+ingredient.getRequiredMicroMJ()/1000000+" MJ/t");
    }

    @Override
    public void render(Minecraft minecraft, int x, int y, @Nullable Laser laser) {
        registerDrawables();

        laser_bar.draw(minecraft,x,y);
        long microMJ = laser != null ? laser.getRequiredMicroMJ() : 0;
        int maxFill = (int) ((63 * microMJ) / RequirementLaser.highestRequiredMJ);
        if(maxFill == 0 && microMJ > 0)
            maxFill = 1;

        long l = System.currentTimeMillis();
        int currentFill = (int) ((l / 200D) % maxFill+1);
        GL11.glColor4ub((byte) 64, (byte) 64,(byte) 64, (byte) 255);
        laser_bar_fill.draw(minecraft,x+1,y+1,63-maxFill,0,0,0);
        GL11.glColor4ub((byte) 255, (byte) 255,(byte) 255, (byte) 255);
        laser_bar_fill.draw(minecraft,x+1,y+1,63-currentFill,0,0,0);
    }
}
