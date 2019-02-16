package modulardiversity.components.requirements;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import modulardiversity.components.ComponentWeather;
import modulardiversity.components.MachineComponents;
import modulardiversity.components.MachineComponents.BiomeDetector;
import modulardiversity.components.MachineComponents.WeatherDetector;
import modulardiversity.components.ComponentWeather;
import modulardiversity.jei.JEIComponentWeather;
import modulardiversity.jei.ingredients.Weather;
import modulardiversity.tile.TileEntityWeatherDetector;

import java.util.ArrayList;
import java.util.List;

public class RequirementWeather extends ComponentRequirement<Weather> {

    private int weather;

    public RequirementWeather(ComponentType componentType, MachineComponent.IOType actionType, int weather) {
        super(componentType, MachineComponent.IOType.INPUT);
        this.weather = weather;
    }

    @Override
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
    }

    @Override
    public ComponentRequirement deepCopy() {
        RequirementWeather requirementWeather = new RequirementWeather(new ComponentWeather(), this.getActionType(), this.weather);
        return requirementWeather;
    }

    @Override
    public void startRequirementCheck(ResultChance resultChance, RecipeCraftingContext recipeCraftingContext) {
    }

    @Override
    public void endRequirementCheck() {
    }

    @Override
    public JEIComponent<Weather> provideJEIComponent() {
        return new JEIComponentWeather(this);
    }

    public int getWeather() {
        return this.weather;
    }
}
