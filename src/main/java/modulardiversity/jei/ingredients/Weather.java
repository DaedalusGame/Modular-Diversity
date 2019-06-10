package modulardiversity.jei.ingredients;

import modulardiversity.components.requirements.RequirementWeather;
import modulardiversity.jei.IFakeIngredient;

import java.util.ArrayList;

public class Weather implements IFakeIngredient {
    private RequirementWeather.Type weather;

    public Weather(RequirementWeather.Type weather) {
        this.weather = weather;
    }

    public RequirementWeather.Type getWeather() {
        return weather;
    }

    public static String getFormattedWeather(RequirementWeather.Type i) {
        switch(i)
        {
            case CLEAR:
                return "Clear";
            case RAIN:
                return "Rain";
            case STORM:
                return "Storm";
            case SNOW:
                return "Snow";
            default:
                return "Unknown";
        }
    }

    @Override
    public String getDisplayName() {
        return "Weather Required: " + getFormattedWeather(weather);
    }

    @Override
    public String getUniqueID() {
        return "weather";
    }
}
