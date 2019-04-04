package de.saibotk.jmaw.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This is the response of the Mojang API containing the current user id and username for a given username.
 *
 * @author saibotk
 * @since 1.0
 */
public class MojangAPIUUIDInfo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("demo")
    @Expose
    private boolean demo;

    @SerializedName("legacy")
    @Expose
    private boolean legacy;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    /**
     * Returns the error string.
     * @return the error string or null if no error has occurred.
     * @since 1.0
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error string.
     * @param error the error string.
     * @since 1.0
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the error message.
     * @return the error message or null if no error has occurred.
     * @since 1.0
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * @param errorMessage the error message.
     * @since 1.0
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns if the account is a demo account.
     * @return true if it is and false if not.
     * @since 1.0
     */
    public boolean isDemo() {
        return demo;
    }

    /**
     * Set if an account is a demo account.
     * @param demo
     * @since 1.0
     */
    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    /**
     * Returns if an account is a legacy account.
     * @return true if it is and false if not.
     * @since 1.0
     */
    public boolean isLegacy() {
        return legacy;
    }

    /**
     * Set if an account is a legacy account.
     * @param legacy
     * @since 1.0
     */
    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    /**
     * Returns the UUID.
     * @return the UUID or null if an error occurred.
     * @since 1.0
     */
    public String getId() {
        return id;
    }

    /**
     * Set the UUID.
     * @param id the uuid.
     * @since 1.0
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the username.
     * @return the username or null if an error occurred.
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Set the username.
     * @param name the username.
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
    }

}