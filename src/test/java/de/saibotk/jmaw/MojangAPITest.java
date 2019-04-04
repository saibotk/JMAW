package de.saibotk.jmaw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.saibotk.jmaw.adapters.MapMojangAPIStatusTypeAdapter;
import de.saibotk.jmaw.models.MojangAPIStatus;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Abstract test class to allow subclasses to easily access a mocked Retrofit instance.
 *
 * @author saibotk
 */
abstract class MojangAPITest {

    /**
     * Get a mocked {@link Retrofit} instance to be used in tests.
     * @param mockWebServer the {@link MockWebServer} instance to use.
     * @return a new Retrofit instance.
     */
    Retrofit getRetrofit(MockWebServer mockWebServer) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MojangAPIStatus.class, new MapMojangAPIStatusTypeAdapter())
                .create();

        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
