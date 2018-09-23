package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

import java.util.ArrayList;

public class Weather implements IFakeIngredient {
    private int weather;

    public Weather(int weather) {
        this.weather = weather;
    }

    public int getWeather() {
        return weather;
    }

    public static String getFormattedWeather(int i) {
        return (i == 2) ? "Thundering" : (i == 1) ? "Raining" : "Clear";
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
