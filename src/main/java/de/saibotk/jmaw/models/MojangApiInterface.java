package de.saibotk.jmaw.models;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * MojangApiInterface is an interface designed to represent all API calls (HTTP) that will be executed with
 * <code>Retrofit</code> and the models that the call will yield when executed.
 *
 * @author saibotk
 * @since 1.0
 */
public interface MojangApiInterface {

    @GET("check")
    Call<MojangAPIStatus> getMojangAPIStatus();

    @GET("users/profiles/minecraft/{username}")
    Call<MojangAPIUUIDInfo> getMojangAPIUUIDInfo(@Path("username") String username);

    @GET("users/profiles/minecraft/{username}")
    Call<MojangAPIUUIDInfo> getMojangAPIUUIDInfo(@Path("username") String username, @Query("at") long timestamp );

    @GET("user/profiles/{uuid}/names")
    Call<List<MojangAPIUsernameItem>> getMojangAPIUsernameHistory(@Path("uuid") String uuid);

    @POST("profiles/minecraft")
    @Headers("Content-Type: application/json")
    Call<List<MojangAPIUUIDInfo>> getMojangAPIUsernamesToUUIDs(@Body List<String> usernames);
 }
