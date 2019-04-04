package de.saibotk.jmaw.adapters;

import com.google.gson.*;
import de.saibotk.jmaw.models.MojangAPIStatus;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A TypeAdapter only implementing the {@link JsonDeserializer} interface since we will never send a
 * {@link MojangAPIStatus}. This is used by {@link Gson} to convert the JSON data received from the API call to a
 * {@link MojangAPIStatus} instance.
 *
 * @author saibotk
 * @since 1.0
 */
public class MapMojangAPIStatusTypeAdapter implements JsonDeserializer<MojangAPIStatus>
{
    private Gson gson = new Gson();

    /**
     * This will deserialize the given JSON to an instance of {@link MojangAPIStatus}.
     *
     * @see JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)
     * @since 1.0
     */
    public MojangAPIStatus deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
    {
        JsonArray json = element.getAsJsonArray();

        Map<String, MojangAPIStatus.MojangAPIStatusCode> map = new HashMap<>();

        json.forEach( entry -> {
            JsonObject jsonObj = entry.getAsJsonObject();
            Set<String> attributes = jsonObj.keySet();
            if (attributes.isEmpty()) return;
            String name = (String) attributes.toArray()[0];
            String statusStr = gson.fromJson(jsonObj.get(name), String.class);
            MojangAPIStatus.MojangAPIStatusCode status = MojangAPIStatus.MojangAPIStatusCode.valueOf(statusStr.toUpperCase());
            map.put(name, status);
        });

        MojangAPIStatus mas = new MojangAPIStatus();
        mas.setServices(map);

        return mas;
    }
}