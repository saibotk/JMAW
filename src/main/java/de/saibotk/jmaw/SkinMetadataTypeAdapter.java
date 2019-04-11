package de.saibotk.jmaw;

import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * This is used by {@link Gson} to convert the JSON data received from the API call to a
 * {@link SkinMetadata} instance.
 *
 * @author saibotk
 * @since 1.0
 */
class SkinMetadataTypeAdapter implements JsonDeserializer<SkinMetadata>, JsonSerializer<SkinMetadata> {
    private Gson gson = new Gson();

    /**
     * This will deserialize the given JSON to an instance of {@link SkinMetadata}.
     *
     * @see JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)
     * @since 1.0
     */
    public SkinMetadata deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
    {
        JsonObject jsonObj = element.getAsJsonObject();
        SkinMetadata sm = new SkinMetadata();
        if (jsonObj.has("model") && gson.fromJson(jsonObj.get("model"), String.class).equals("slim")) {
            sm.setModel(SkinMetadata.SkinModel.SLIM);
        } else {
            sm.setModel(SkinMetadata.SkinModel.DEFAULT);
        }
        return sm;
    }

    /**
     * Serializes the given {@link SkinMetadata} instance to a {@link JsonElement}.
     *
     * @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
     * @since 1.0
     */
    @Override
    public JsonElement serialize(SkinMetadata src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        if(src.getModel() == SkinMetadata.SkinModel.SLIM) {
            jo.addProperty("model", "slim");
        } else {
            jo.addProperty("model", "");
        }
        return jo;
    }
}