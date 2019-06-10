package modulardiversity.jei.renderer;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import modulardiversity.jei.JEIHelpers;
import modulardiversity.jei.ingredients.DimensionIngredient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RendererDimension implements IIngredientRenderer<DimensionIngredient> {
    static IDrawable grassBlock;

    private void registerDrawables() {
        if (grassBlock == null)
            grassBlock = JEIHelpers.GUI_HELPER.createDrawable(new ResourceLocation("minecraft", "textures/blocks/grass_side.png"), 0, 0, 16, 16, 16, 16);
    }

    @Override
    public void render(Minecraft minecraft, int i, int i1, @Nullable DimensionIngredient biomeIngredient) {
        registerDrawables();

        grassBlock.draw(minecraft, i, i1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<String> getTooltip(Minecraft minecraft, DimensionIngredient ingredient, ITooltipFlag tooltipFlag) {
        return Lists.newArrayList("Required Dimension: " + ingredient.getDisplayName());
    }
}