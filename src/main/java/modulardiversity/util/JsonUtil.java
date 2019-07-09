package modulardiversity.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JsonUtil {
    public static void onlyOne(JsonObject json, String... names)
    {
        boolean hasNamePrev = false;
        for (String name : names) {
            boolean hasName = json.has(name);
            if (hasNamePrev && hasName)
                throw new JsonParseException("Element cannot have ");
            hasNamePrev = hasName;
        }
    }

    public static String get(JsonObject json, String name, String defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsString();
        else
            return defaultValue;
    }

    public static boolean get(JsonObject json, String name, boolean defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsBoolean();
        else
            return defaultValue;
    }

    public static int get(JsonObject json, String name, int defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsInt();
        else
            return defaultValue;
    }

    public static long get(JsonObject json, String name, long defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsLong();
        else
            return defaultValue;
    }

    public static float get(JsonObject json, String name, float defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsFloat();
        else
            return defaultValue;
    }

    public static double get(JsonObject json, String name, double defaultValue)
    {
        if(json.has(name))
            return json.getAsJsonPrimitive(name).getAsDouble();
        else
            return defaultValue;
    }

    public static boolean getPerTick(JsonObject json) {
        return getPerTick(json,false);
    }

    public static boolean getPerTick(JsonObject json, boolean defaultValue) {
        return get(json,"perTick",defaultValue);
    }
}
