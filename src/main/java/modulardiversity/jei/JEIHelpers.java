package modulardiversity.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import modulardiversity.ModularDiversity;
import modulardiversity.jei.ingredients.*;
import modulardiversity.jei.renderer.RendererEmber;
import modulardiversity.jei.renderer.RendererMechanical;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

@JEIPlugin
public class JEIHelpers implements IModPlugin {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ModularDiversity.MODID,"textures/gui/widgets.png");
    public static IGuiHelper GUI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        GUI_HELPER = registry.getJeiHelpers().getGuiHelper();
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        registry.register(Mechanical.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMechanical());
        registry.register(Air.class, new ArrayList<>(), new FakeIngredientHelper<>(), new FakeIngredientRenderer<>());
        registry.register(Embers.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererEmber());
        registry.register(Mana.class, new ArrayList<>(), new FakeIngredientHelper<>(), new FakeIngredientRenderer<>());
        registry.register(Laser.class, new ArrayList<>(), new FakeIngredientHelper<>(), new FakeIngredientRenderer<>());
    }
}
