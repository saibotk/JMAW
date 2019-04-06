package de.saibotk.jmaw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private static final int MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS = 100;

    private static final String MOJANG_API_STATUS_URL = "https://status.mojang.com";
    private static final String MOJANG_API_URL = "https://api.mojang.com";

    ApiInterface statusAPIInterface;
    ApiInterface mojangAPIInterface;

    /**
     * Constructor of the MojangAPI class, that will initialize all internal objects used to make requests and
     * deserialize them.
     */
    public MojangAPI() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(APIStatus.class, new MapAPIStatusTypeAdapter())
                .create();

        Retrofit retrofitStatusAPI = new Retrofit.Builder()
                .baseUrl(MOJANG_API_STATUS_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Retrofit retrofitMojangAPI = new Retrofit.Builder()
                .baseUrl(MOJANG_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        statusAPIInterface = retrofitStatusAPI.create(ApiInterface.class);
        mojangAPIInterface = retrofitMojangAPI.create(ApiInterface.class);
    }

    private <T> T request(Call<T> callToExecute, String url) throws ApiResponseException {
        Response<T> response = null;

        // try requesting data from the server
        try {
            response = callToExecute.execute();
        } catch (IOException e) {
            logger.log(Level.INFO, MESSAGE_ERROR_ON_CONNECT + url);
        }

        if (response != null) {
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new ApiResponseException(response);
            }
        }
        return null;
    }

    /**
     * This will query the Mojang API for a response about the status of its services.
     *
     * @return an instance of {@link APIStatus} or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public APIStatus getAPIStatus() throws ApiResponseException {
        return request(mojangAPIInterface.getMojangAPIStatus(), MOJANG_API_STATUS_URL);
    }

    /**
     * This will query the Mojang API for a response about the current UUID of a player by its username.
     *
     * @param username the players username.
     * @return an instance of {@link UUIDInfo} or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public UUIDInfo getUUIDInfo(String username) throws ApiResponseException {
        return request(mojangAPIInterface.getMojangAPIUUIDInfo(username), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a response about the current UUID of a player by its username at a
     * given timestamp.
     *
     * @param username the players username.
     * @param timestamp the timestamp.
     * @return an instance of {@link UUIDInfo} or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public UUIDInfo getUUIDInfo(String username, long timestamp) throws ApiResponseException {
        return request(mojangAPIInterface.getMojangAPIUUIDInfo(username, timestamp), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a response about the username history for a player by his uuid.
     *
     * @param uuid the unique user id of the player.
     * @return a {@link List< UsernameItem >} instance or <code>null</code> if the servers response is empty or
     *         an error occured while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public List<UsernameItem> getUsernameHistory(String uuid) throws ApiResponseException {
        return request(mojangAPIInterface.getMojangAPIUsernameHistory(uuid), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a response about the username history for a player by his uuid.
     *
     * @param usernames list of usernames to query.
     * @return a {@link List< UUIDInfo >} instance or <code>null</code> if the servers response is empty or
     *         an error occured while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public List<UUIDInfo> getUUIDsForUsernames(List<String> usernames) throws ApiResponseException {

        int requestsNeeded = usernames.size() / MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS + 1;
        int sizeToOperateOn = usernames.size();
        List<UUIDInfo> responseList = new ArrayList<>();

        for (int i = 0; i < requestsNeeded; i++) {

            List<String> usernamesToRequest = usernames.subList(MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS * i, MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS * i + Math.min(sizeToOperateOn, MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS));

            // request data
            List<UUIDInfo> response = request(mojangAPIInterface.getMojangAPIUsernamesToUUIDs(usernamesToRequest), MOJANG_API_URL);

            if (response != null && !response.isEmpty()) {
                responseList.addAll(response);
            }

            sizeToOperateOn -= MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS;
        }

        return (!responseList.isEmpty()) ? responseList : null;
    }

}
