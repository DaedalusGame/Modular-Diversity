package modulardiversity.util;

import com.google.gson.JsonObject;

public class JsonUtil {
    public static void assertOnlyOne(JsonObject json, String... names)
    {
        boolean hasNamePrev = false;
        for (String name : names) {
            boolean hasName = json.has(name);
            assert (!hasName || !hasNamePrev);
            hasNamePrev = hasName;
        }
    }
}
