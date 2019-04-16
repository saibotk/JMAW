package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

/**
 * Abstract test class to allow subclasses to easily access a mocked Retrofit instance.
 *
 * @author saibotk
 */
abstract class APITest {

    /**
     * Get a mocked {@link Retrofit} instance to be used in tests.
     * @param mockWebServer the {@link MockWebServer} instance to use.
     * @return a new Retrofit instance.
     * @since 1.0
     */
    Retrofit getRetrofit(MockWebServer mockWebServer) {
        return Util.getRetrofitBuilder()
                .baseUrl(mockWebServer.url("").toString())
                .build();
    }

    /**
     * Get a mocked {@link Retrofit} instance to be used in tests.
     * @param mockWebServer the {@link MockWebServer} instance to use.
     * @param builder pre-defined {@link Retrofit.Builder} instance instead of the default.
     * @return a new Retrofit instance.
     * @since 1.0
     */
    Retrofit getRetrofit(MockWebServer mockWebServer, Retrofit.Builder builder) {
        return builder
                .baseUrl(mockWebServer.url("").toString())
                .build();
    }
}
