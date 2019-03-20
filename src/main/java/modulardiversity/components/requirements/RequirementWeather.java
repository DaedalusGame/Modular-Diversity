package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import modulardiversity.jei.JEIComponentWeather;
import modulardiversity.jei.ingredients.Weather;
import modulardiversity.util.IResourceToken;
import modulardiversity.util.Misc;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RequirementWeather extends RequirementEnvironmental<Weather, RequirementWeather.ResourceToken> {
    public enum Type {
        CLEAR,
        RAIN,
        STORM,
        SNOW
    }

    public Type weather;

    public RequirementWeather(MachineComponent.IOType actionType, Type weather) {
        super(ComponentType.Registry.getComponent("weather"), actionType);
        this.weather = weather;
    }

   /* @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof MachineComponents.WeatherDetector && component.getIOType() == this.getActionType()) {
            int trueWeather = ((MachineComponents.WeatherDetector) component).getContainerProvider();
            return this.weather == trueWeather;
        }
        return false;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof MachineComponents.WeatherDetector && component.getIOType() == this.getActionType()) {
            return true;
        }
        return false;
    }

    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (component.getComponentType().equals(this.getRequiredComponentType()) && component instanceof MachineComponents.WeatherDetector && component.getIOType() == this.getActionType()) {
            int trueWeather = ((MachineComponents.WeatherDetector) component).getContainerProvider();
            return this.weather == trueWeather ? CraftCheck.SUCCESS : CraftCheck.PARTIAL_SUCCESS;
        }
        return CraftCheck.INVALID_SKIP;
    }*/

    @Override
    public ComponentRequirement<Weather> deepCopy() {
        return new RequirementWeather(this.getActionType(), this.weather);
    }

    @Override
    public ComponentRequirement<Weather> deepCopyModified(List<RecipeModifier> modifiers) {
        return new RequirementWeather(this.getActionType(), this.weather);
    }

    /*@Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }*/

    @Override
    public JEIComponent<Weather> provideJEIComponent() {
        return new JEIComponentWeather(this);
    }

    @Override
    protected ResourceToken emitConsumptionToken(RecipeCraftingContext context) {
        return new ResourceToken();
    }

    private boolean isValidWeather(World world, BlockPos pos) {
        switch (weather) {
            case CLEAR:
                return !world.isRaining();
            case RAIN:
                return world.isRaining();
            case STORM:
                return world.isThundering();
            case SNOW:
                return world.isRaining() && world.canSnowAt(pos, false);
            default:
                return false;
        }
    }

    private boolean setWeather(World world, BlockPos pos) {
        switch (weather) {
            case CLEAR:
                if (world.isRaining()) {
                    world.setRainStrength(0);
                    return true;
                }
                return false;
            case RAIN:
                if (!world.isRaining()) {
                    world.setRainStrength(1);
                    return true;
                }
                return false;
            case STORM:
                if (!world.isThundering()) {
                    world.setThunderStrength(1);
                    return true;
                }
                return false;
            case SNOW:
                if (!world.canSnowAt(pos, false))
                    return false;
                if (!world.isRaining()) {
                    world.setRainStrength(1);
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    @Override
    protected boolean consumeToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doConsume) {
        TileEntity tile = Misc.getTileEntity(component);
        if (tile != null)
            if (isValidWeather(tile.getWorld(), tile.getPos())) {
                token.setRequirementMet();
            }
        return true;
    }

    @Override
    protected boolean generateToken(MachineComponent component, RecipeCraftingContext context, ResourceToken token, boolean doGenerate) {
        TileEntity tile = Misc.getTileEntity(component);
        if (tile != null)
            if (setWeather(tile.getWorld(), tile.getPos())) {
                token.setRequirementMet();
            }
        return true;
    }

    public static class ResourceToken implements IResourceToken {
        private boolean requirementMet;

        public ResourceToken() {
        }

        public void setRequirementMet() {
            requirementMet = true;
        }

        @Override
        public void applyModifiers(RecipeCraftingContext modifiers, MachineComponent.IOType ioType, float durationMultiplier) {

        }

        @Override
        public boolean isEmpty() {
            return requirementMet;
        }
    }
}
