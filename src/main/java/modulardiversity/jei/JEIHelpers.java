package modulardiversity.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import modulardiversity.ModularDiversity;
import modulardiversity.jei.ingredients.*;
import modulardiversity.jei.renderer.*;
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
        registry.register(Mana.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMana());
        registry.register(Laser.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererLaser());
        registry.register(HotAir.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererHotAir());
        registry.register(BiomeIngredient.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererBiome());
        registry.register(DaylightIngredient.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererDaylight());
        registry.register(Weather.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererWeather());
        registry.register(MekLaser.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMekLaser());
        registry.register(MekHeat.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMekHeat());
        registry.register(MysticalMechanics.class, new ArrayList<>(), new FakeIngredientHelper<>(), new RendererMysticalMechanics());
    }
}
