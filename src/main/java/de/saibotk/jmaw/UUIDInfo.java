package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This is the response of the Mojang API containing the current user id and username for a given username.
 *
 * @author saibotk
 * @since 1.0
 */
public class UUIDInfo {

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
     * This will not modify anything on the Mojang account / API.
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
     * This will not modify anything on the Mojang account / API.
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
     * This will not modify anything on the Mojang account / API.
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
     * This will not modify anything on the Mojang account / API.
     * @param name the username.
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
    }

}