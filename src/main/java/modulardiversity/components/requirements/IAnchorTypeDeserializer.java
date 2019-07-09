package modulardiversity.components.requirements;

import com.google.gson.JsonObject;

public interface IAnchorTypeDeserializer {
    AnchorType deserialize(JsonObject json);
}
