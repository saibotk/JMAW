package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

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
    void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the timestamp, when the user started using the name (in milliseconds).
     * @return the timestamp.
     * @since 1.0
     */
    public Optional<Long> getChangedToAt() {
        return Optional.ofNullable(changedToAt);
    }

    /**
     * Sets the timestamp, for when the user started using the name.
     * This will not modify anything on the Mojang account / API.
     * @param changedToAt the timestamp.
     * @since 1.0
     */
    void setChangedToAt(Long changedToAt) {
        this.changedToAt = changedToAt;
    }

}