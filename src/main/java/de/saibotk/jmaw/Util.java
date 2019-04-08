package de.saibotk.jmaw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Utility class holding static functions to recreate commonly used instances etc.
 *
 * @author saibotk
 * @since 1.0
 */
class Util {

    private Util() { }

    /**
     * Returns a {@link Gson} instance, prepared with the commonly used custom type adapters.
     * @return an {@link Gson} instance.
     * @since 1.0
     */
    static Gson getGson() {
        RuntimeDefaultTypeAdapterFactory<PlayerProperty> playerPropertyAdapterFactory = RuntimeDefaultTypeAdapterFactory.of(PlayerProperty.class, "name", true);
        playerPropertyAdapterFactory.registerSubtype(PlayerTexturesProperty.class, "textures");

        return new GsonBuilder()
                .registerTypeAdapter(PlayerTexturesProperty.class, new PlayerTexturesPropertyDeserializer())
                .registerTypeAdapter(APIStatus.class, new APIStatusDeserializer())
                .registerTypeAdapterFactory(playerPropertyAdapterFactory)
                .create();
    }
}
