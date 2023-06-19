package pl.psi.map;

import com.google.gson.*;
import pl.psi.mapElements.MapElement;

import java.lang.reflect.Type;

public class MapElementAdapter implements JsonSerializer<MapElement>, JsonDeserializer<MapElement> {
    @Override
    public JsonElement serialize(MapElement element, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", element.getClass().getName());
        jsonObject.add("data", context.serialize(element));
        return jsonObject;
    }

    @Override
    public MapElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String className = jsonObject.get("type").getAsString();
        JsonElement data = jsonObject.get("data");

        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(data, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Nie można zdeserializować obiektu MapElement.", e);
        }
    }
}
