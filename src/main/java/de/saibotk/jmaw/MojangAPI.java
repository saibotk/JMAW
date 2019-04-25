package de.saibotk.jmaw;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This Mojang API wrapper class contains all functions to interact with the Mojang API and will return its answers as
 * instances of their respective model classes found in {@link de.saibotk.jmaw}.
 *
 * @author saibotk
 * @since 1.0
 */
@SuppressWarnings("WeakerAccess")
public class MojangAPI {

    private static final Logger logger = Logger.getLogger(MojangAPI.class.getName());

    private static final String MESSAGE_ERROR_ON_CONNECT = "Failed to connect to ";
    private static final int MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS = 100;

    private static final String MOJANG_API_STATUS_URL = "https://status.mojang.com";
    private static final String MOJANG_API_URL = "https://api.mojang.com";
    private static final String MOJANG_API_SESSION_URL = "https://sessionserver.mojang.com";

    ApiInterface statusAPIInterface;
    ApiInterface mojangAPIInterface;
    ApiInterface sessionAPIInterface;
    ApiInterface sessionAPILineReadInterface;

    /**
     * Constructor of the MojangAPI class, that will initialize all internal objects used to make requests and
     * deserialize them.
     */
    public MojangAPI() {
        Retrofit retrofitStatusAPI = Util.getRetrofitBuilder()
                .baseUrl(MOJANG_API_STATUS_URL)
                .build();

        Retrofit retrofitMojangAPI = Util.getRetrofitBuilder()
                .baseUrl(MOJANG_API_URL)
                .build();

        Retrofit retrofitSessionAPI = Util.getRetrofitBuilder()
                .baseUrl(MOJANG_API_SESSION_URL)
                .build();

        Retrofit retrofitLineReadSessionAPI = Util.getStringLineRetrofitBuilder()
                .baseUrl(MOJANG_API_SESSION_URL)
                .build();

        statusAPIInterface = retrofitStatusAPI.create(ApiInterface.class);
        mojangAPIInterface = retrofitMojangAPI.create(ApiInterface.class);
        sessionAPIInterface = retrofitSessionAPI.create(ApiInterface.class);
        sessionAPILineReadInterface = retrofitLineReadSessionAPI.create(ApiInterface.class);
    }

    private <T> Optional<T> request(Call<T> callToExecute, String url) throws ApiResponseException {
        Response<T> response = null;

        // try requesting data from the server
        try {
            response = callToExecute.execute();
        } catch (IOException e) {
            logger.log(Level.INFO, MESSAGE_ERROR_ON_CONNECT + url);
        }

        if (response != null) {
            if (response.isSuccessful()) {
                return Optional.ofNullable(response.body());
            } else {
                ApiResponseException ex = new ApiResponseException(response);
                if (ex.getApiError().isPresent() && ex.getApiError().get().getError().equals("TooManyRequestsException")) {
                    throw new TooManyRequestsException(response);
                }
                throw ex;
            }
        }
        return Optional.empty();
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
    public Optional<APIStatus> getAPIStatus() throws ApiResponseException {
        return request(statusAPIInterface.getApiStatus(), MOJANG_API_STATUS_URL);
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
    public Optional<UUIDInfo> getUUIDInfo(String username) throws ApiResponseException {
        return request(mojangAPIInterface.getUUIDInfo(username), MOJANG_API_URL);
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
    public Optional<UUIDInfo> getUUIDInfo(String username, long timestamp) throws ApiResponseException {
        return request(mojangAPIInterface.getUUIDInfo(username, timestamp), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a response about the username history for a player by his uuid.
     *
     * @param uuid the unique user id of the player.
     * @return a {@link List} instance or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public Optional<List<UsernameItem>> getUsernameHistory(String uuid) throws ApiResponseException {
        return request(mojangAPIInterface.getUsernameHistory(uuid), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a response about the username history for a player by his uuid.
     * The list might be empty or the item count may differ from the input count, since the API ignores wrong uuids.
     *
     * @param usernames list of usernames to query.
     * @return a {@link List} instance or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
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
            Optional<List<UUIDInfo>> result = request(mojangAPIInterface.getUsernamesToUUIDs(usernamesToRequest), MOJANG_API_URL);

            result.ifPresent(responseList::addAll);

            sizeToOperateOn -= MOJANG_API_USERNAMES_TO_UUIDS_MAX_REQUESTS;
        }

        return responseList;
    }

    /**
     * This will query the Mojang API for a response about the informations (eg. skin etc) of a specific player by his
     * uuid.
     * This response will be unsigned.
     *
     * @param uuid the unique user id of the player.
     * @return an instance of {@link PlayerProfile} or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public Optional<PlayerProfile> getPlayerProfile(String uuid) throws ApiResponseException {
        return request(sessionAPIInterface.getPlayerProfile(uuid, true), MOJANG_API_SESSION_URL);
    }

    /**
     * This will query the Mojang API for a response about the informations (eg. skin etc) of a specific player by his
     * uuid.
     * This response will contain a signature by the server, if requested.
     *
     * @param uuid the unique user id of the player.
     * @param signed if the request should be signed by the server.
     * @return an instance of {@link PlayerProfile} or <code>null</code> if the servers response is empty or
     *         an error occurred while connecting.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public Optional<PlayerProfile> getPlayerProfile(String uuid, boolean signed) throws ApiResponseException {
        return request(sessionAPIInterface.getPlayerProfile(uuid, !signed), MOJANG_API_SESSION_URL);
    }

    /**
     * This will query the Mojang API for a change of the players skin, by sending a url to the skin file.
     *
     * @param uuid the unique user id of the player.
     * @param token the api token.
     * @param url the link to the new skin.
     * @param model the model type of the new skin.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public void changeSkin(String uuid, String token, String url, SkinMetadata.SkinModel model) throws ApiResponseException {
        request(mojangAPIInterface.setSkin(uuid, token, url, model.toString()), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a change of the players skin, by sending the skin file.
     *
     * @param uuid the unique user id of the player.
     * @param token the api token.
     * @param skin the new skin file.
     * @param model the model type of the new skin.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public void uploadSkin(String uuid, String token, File skin, SkinMetadata.SkinModel model) throws ApiResponseException {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", skin.getName(), RequestBody.create(MediaType.parse("image/*"), skin));
        request(mojangAPIInterface.uploadSkin(uuid, token, filePart, model.toString()), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API to delete the players skin.
     *
     * @param uuid the unique user id of the player.
     * @param token the api token.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public void deleteSkin(String uuid, String token) throws ApiResponseException {
        request(mojangAPIInterface.deleteSkin(uuid, token), MOJANG_API_URL);
    }

    /**
     * This will query the Mojang API for a list of blocked servers.
     *
     * @return {@link List} list with all blocked servers hashes.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public Optional<List<String>> getBlockedServers() throws ApiResponseException {
        return request(sessionAPILineReadInterface.getBlockedServers(), MOJANG_API_SESSION_URL);
    }

    /**
     * This will query the Mojang API for informations about the sales for specific products.
     *
     * @param keys the metric keys to request the statistics for.
     * @return {@link SaleStatistics} instance.
     * @throws ApiResponseException This will occur when the API returns an error code and the user input might be
     *                              incorrect or there is an internal server error.
     * @since 1.0
     */
    public Optional<SaleStatistics> getSaleStatistics(List<SaleStatistics.MetricKeys> keys) throws ApiResponseException {
        List<String> distinctKeys = keys.stream().distinct().map(k -> k.name().toLowerCase()).collect(Collectors.toList());
        JsonObject metrics = new JsonObject();
        metrics.add("metricKeys", Util.getGson().toJsonTree(distinctKeys));
        return request(mojangAPIInterface.getStatistics(metrics), MOJANG_API_URL);
    }
}
