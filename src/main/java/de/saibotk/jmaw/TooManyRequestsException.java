package de.saibotk.jmaw;

import retrofit2.Response;

/**
 * This represents the TooManyRequestsException returned by the API.
 *
 * @author saibotk
 * @since 1.0
 */
public class TooManyRequestsException extends ApiResponseException {

    /**
     * The TooManyRequestsException constructor will set the error message and the response associated with the exception.
     *
     * @param response the response associated to the exception.
     * @since 1.0
     */
    TooManyRequestsException(Response response) {
        super(response);
    }
}
