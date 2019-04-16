package de.saibotk.jmaw;

import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

/**
 * This represents an API exception, for example when the server responds with an HTTP 400 message.
 * The instance will yield the {@linkplain #response} associated with the exception.
 *
 * @author saibotk
 * @since 1.0
 */
public class ApiResponseException extends IOException {

    public final transient Response response;

    /**
     * The ApiResponseException constructor will set the error message and the response associated with the exception.
     * @param response the response associated to the exception.
     * @since 1.0
     */
    ApiResponseException(Response response) {
        super(response.code() + ": [" + response.message() + "]");
        this.response = response;
    }

    /**
     * This will yield the HTTP status code for the associated response.
     * @return the status code.
     * @since 1.0
     */
    public int getStatusCode() {
        return response.code();
    }

    /**
     * This will yield the error body from the {@linkplain #response}.
     * @return the error body.
     * @since 1.0
     */
    public ResponseBody getErrorBody() {
        return response.errorBody();
    }

}
