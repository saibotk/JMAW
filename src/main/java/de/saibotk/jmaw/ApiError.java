package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Simple error model. This is sometimes returned by the API for requests that are invalid or eg. when the rate limit
 * was exceeded.
 *
 * @author saibotk
 * @since 1.0
 */
public class ApiError {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    /**
     * Returns the error name.
     *
     * @return name of the error.
     * @since 1.0
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error name.
     * This will not modify anything on the Mojang account / API.
     *
     * @param error the error name.
     * @since 1.0
     */
    void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the error message.
     *
     * @return error message.
     * @since 1.0
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * This will not modify anything on the Mojang account / API.
     * @param errorMessage error message.
     * @since 1.0
     */
    void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
