package de.saibotk.jmaw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.saibotk.jmaw.adapters.MapMojangAPIStatusTypeAdapter;
import de.saibotk.jmaw.models.MojangAPIStatus;
import de.saibotk.jmaw.models.MojangAPIUUIDInfo;
import de.saibotk.jmaw.models.MojangApiInterface;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Mojang API wrapper class contains all functions to interact with the Mojang API and will return its answers as
 * instances of their respective model classes found in <code>de.saibotk.jmaw.models</code>.
 *
 * @author saibotk
 * @since 1.0
 */
public class MojangAPI {
    private static final Logger logger = Logger.getLogger(MojangAPI.class.getName());

    private static final String MESSAGE_ERROR_ON_CONNECT = "Failed to connect to ";

    private static final String MOJANG_API_STATUS_URL = "https://status.mojang.com";
    private static final String MOJANG_API_URL = "https://api.mojang.com";

    private Gson gson;

    MojangApiInterface statusAPIInterface;
    MojangApiInterface mojangAPIInterface;

    /**
     * Constructor of the MojangAPI class, that will initialize all internal objects used to make requests and
     * deserialize them.
     */
    public MojangAPI() {
        gson = new GsonBuilder()
                .registerTypeAdapter(MojangAPIStatus.class, new MapMojangAPIStatusTypeAdapter())
                .create();

        Retrofit retrofitStatusAPI = new Retrofit.Builder()
                .baseUrl(MOJANG_API_STATUS_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Retrofit retrofitMojangAPI = new Retrofit.Builder()
                .baseUrl(MOJANG_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        statusAPIInterface = retrofitStatusAPI.create(MojangApiInterface.class);
        mojangAPIInterface = retrofitMojangAPI.create(MojangApiInterface.class);
    }

    /**
     * This will query the Mojang API for a response about the status of its services.
     *
     * @return an instance of {@link MojangAPIStatus} or <code>null</code> if the servers response is empty or
     *         an error occurred.
     * @since 1.0
     */
    public MojangAPIStatus getAPIStatus() {

        Response<MojangAPIStatus> response = null;

        // try requesting data from the server
        try {
            response = statusAPIInterface.getMojangAPIStatus().execute();
        } catch (IOException e) {
            logger.log(Level.INFO, MESSAGE_ERROR_ON_CONNECT + MOJANG_API_STATUS_URL);
        }

        return (response != null) ? response.body() : null;
    }

    /**
     * This will query the Mojang API for a response about the current UUID of a player by its username.
     *
     * @param username the players username.
     * @return an instance of {@link MojangAPIUUIDInfo} or <code>null</code> if the servers response is empty or
     *         an error occurred.
     * @since 1.0
     */
    public MojangAPIUUIDInfo getUUIDInfo(String username) {

        Response<MojangAPIUUIDInfo> response = null;

        // try requesting data from the server
        try {
            response = mojangAPIInterface.getMojangAPIUUIDInfo(username).execute();
        } catch (IOException e) {
            logger.log(Level.INFO, MESSAGE_ERROR_ON_CONNECT + MOJANG_API_URL);
        }

        return (response != null) ? response.body() : null;
    }

    /**
     * This will query the Mojang API for a response about the current UUID of a player by its username at a
     * given timestamp.
     *
     * @param username the players username.
     * @param timestamp the timestamp.
     * @return an instance of {@link MojangAPIUUIDInfo} or <code>null</code> if the servers response is empty or
     *         an error occurred.
     * @since 1.0
     */
    public MojangAPIUUIDInfo getUUIDInfo(String username, long timestamp) {

        Response<MojangAPIUUIDInfo> response = null;

        // try requesting data from the server
        try {
            response = mojangAPIInterface.getMojangAPIUUIDInfo(username, timestamp).execute();
        } catch (IOException e) {
            logger.log(Level.INFO, MESSAGE_ERROR_ON_CONNECT + MOJANG_API_URL);
        }

        return (response != null) ? response.body() : null;
    }

}
