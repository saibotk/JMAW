package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This is the response of the Mojang API containing a username and a timestamp,
 * describing when a player started using the name, for a given uuid.
 * Returned in a {@link java.util.List} by {@link de.saibotk.jmaw.MojangAPI#getUsernameHistory(String)}.
 *
 * @author saibotk
 * @since 1.0
 */
public class UsernameItem {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("changedToAt")
    @Expose
    private Long changedToAt;

    /**
     * Returns the username.
     * @return username.
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the username.
     * This will not modify anything on the Mojang account / API.
     * @param name the username.
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the timestamp, when the user started using the name.
     * @return the timestamp.
     * @since 1.0
     */
    public Long getChangedToAt() {
        return changedToAt;
    }

    /**
     * Sets the timestamp, for when the user started using the name.
     * This will not modify anything on the Mojang account / API.
     * @param changedToAt the timestamp.
     * @since 1.0
     */
    public void setChangedToAt(Long changedToAt) {
        this.changedToAt = changedToAt;
    }

}