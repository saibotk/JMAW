package de.saibotk.jmaw;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the response for the Mojang API when querying for information on a specific player via
 * {@link MojangAPI#getPlayerProfile(String)}.
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerProfile {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("properties")
    @Expose
    private List<PlayerProperty> properties = null;

    /**
     * Returns the players uuid.
     * @return the uuid.
     * @since 1.0
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the uuid of a player.
     * This will not modify anything on the Mojang account / API.
     * @param id the uuid.
     * @since 1.0
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the current username for the player.
     * @return the username.
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
     * Returns a list of properties of the player. These are instances of the {@link PlayerProperty} class.
     * @return a list of player properties.
     * @since 1.0
     */
    public List<PlayerProperty> getProperties() {
        return properties;
    }

    /**
     * Sets the player's properties list.
     * This will not modify anything on the Mojang account / API.
     * @param properties the list of properties for a player.
     * @since 1.0
     */
    void setProperties(List<PlayerProperty> properties) {
        this.properties = properties;
    }

}