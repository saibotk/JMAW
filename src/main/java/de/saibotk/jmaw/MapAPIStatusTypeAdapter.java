package de.saibotk.jmaw;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A TypeAdapter only implementing the {@link JsonDeserializer} interface since we will never send a
 * {@link APIStatus}. This is used by {@link Gson} to convert the JSON data received from the API call to a
 * {@link APIStatus} instance.
 *
 * @author saibotk
 * @since 1.0
 */
public class MapAPIStatusTypeAdapter implements JsonDeserializer<APIStatus>
{
    private Gson gson = new Gson();

    /**
     * This will deserialize the given JSON to an instance of {@link APIStatus}.
     *
     * @see JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)
     * @since 1.0
     */
    public APIStatus deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
    {
        JsonArray json = element.getAsJsonArray();

        Map<String, APIStatus.MojangAPIStatusCode> map = new HashMap<>();

        json.forEach( entry -> {
            JsonObject jsonObj = entry.getAsJsonObject();
            Set<String> attributes = jsonObj.keySet();
            if (attributes.isEmpty()) return;
            String name = (String) attributes.toArray()[0];
            String statusStr = gson.fromJson(jsonObj.get(name), String.class);
            APIStatus.MojangAPIStatusCode status = APIStatus.MojangAPIStatusCode.valueOf(statusStr.toUpperCase());
            map.put(name, status);
        });

        APIStatus mas = new APIStatus();
        mas.setServices(map);

        return mas;
    }
}