package de.saibotk.jmaw;

import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

/**
 * This represents an API exception, for example when the server responds with an HTTP 400 message.
 * The instance will yield the {@linkplain #response} associated with the exception.
 *
 * @author saibotk
 * @since 1.0
 */
public class ApiResponseException extends Exception {

    public final transient Response response;

    private final transient ApiError errorResponse;

    /**
     * The ApiResponseException constructor will set the error message and the response associated with the exception.
     * @param response the response associated to the exception.
     * @since 1.0
     */
    ApiResponseException(Response response) {
        super(response.code() + ": [" + response.message() + "]");
        this.response = response;

        ApiError parsedError = null;
        try (ResponseBody body = response.errorBody()) {
            if (body != null) {
                String content = body.string();
                parsedError = new GsonBuilder().create().fromJson(content, ApiError.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.errorResponse = parsedError;
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

    /**
     * Returns the API's response parsed as a {@link ApiError} object, if it contains the typical error and errorMessage fields.
     *
     * @return the parsed {@link ApiError} object.
     * @since 1.0
     */
    public Optional<ApiError> getApiError() {
        return Optional.ofNullable(errorResponse);
    }
}
