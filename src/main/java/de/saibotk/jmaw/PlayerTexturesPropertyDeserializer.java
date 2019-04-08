package de.saibotk.jmaw;

import com.google.gson.*;
import java.util.Base64;
import java.lang.reflect.Type;

/**
 * A TypeAdapter only implementing the {@link JsonDeserializer} interface since we will never send a
 * {@link PlayerTexturesProperty}. This is used by {@link Gson} to convert the JSON data received from the API call to a
 * {@link PlayerTexturesProperty} instance. It will handle decoding the base64 encoded value.
 *
 * @author saibotk
 * @since 1.0
 */
class PlayerTexturesPropertyDeserializer implements JsonDeserializer<PlayerTexturesProperty> {
    private Gson gson = new Gson();

    /**
     * This will deserialize the given JSON to an instance of {@link PlayerTexturesProperty}.
     *
     * @see JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)
     * @since 1.0
     */
    public PlayerTexturesProperty deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
    {
        JsonObject jsonObj = element.getAsJsonObject();
        PlayerTexturesProperty pp = new PlayerTexturesProperty();

        String valueRaw = gson.fromJson(jsonObj.get("value"), String.class);
        JsonObject value = gson.fromJson(new String(Base64.getDecoder().decode(valueRaw)), JsonObject.class);

        pp.setName(gson.fromJson(jsonObj.get("name"), String.class));
        pp.setRawValue(valueRaw);
        pp.setSignature(gson.fromJson(jsonObj.get("signature"), String.class));
        pp.setTextures(gson.fromJson(value.get("textures"), PlayerTextures.class));
        pp.setProfileId(gson.fromJson(value.get("profileId"), String.class));
        pp.setProfileName(gson.fromJson(value.get("profileName"), String.class));
        if (value.has("signatureRequired")) {
            pp.setSignatureRequired(value.getAsJsonPrimitive("signatureRequired").getAsBoolean());
        }
        pp.setTimestamp(gson.fromJson(value.get("timestamp"), Long.class));

        return pp;
    }
}