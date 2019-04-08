package de.saibotk.jmaw;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * ApiInterface is an interface designed to represent all API calls (HTTP) that will be executed with
 * <code>Retrofit</code> and the models that the call will yield when executed.
 *
 * @author saibotk
 * @since 1.0
 */
interface ApiInterface {

    @GET("check")
    Call<APIStatus> getApiStatus();

    @GET("users/profiles/minecraft/{username}")
    Call<UUIDInfo> getUUIDInfo(@Path("username") String username);

    @GET("users/profiles/minecraft/{username}")
    Call<UUIDInfo> getUUIDInfo(@Path("username") String username, @Query("at") long timestamp );

    @GET("user/profiles/{uuid}/names")
    Call<List<UsernameItem>> getUsernameHistory(@Path("uuid") String uuid);

    @POST("profiles/minecraft")
    @Headers("Content-Type: application/json")
    Call<List<UUIDInfo>> getUsernamesToUUIDs(@Body List<String> usernames);

    @GET("session/minecraft/profile/{uuid}")
    Call<PlayerProfile> getPlayerProfile(@Path("uuid") String uuid, @Query("unsigned") boolean unsigned);

 }
