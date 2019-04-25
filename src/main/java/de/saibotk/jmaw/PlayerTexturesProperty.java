package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the response of the Mojang API when querying for a {@link PlayerProfile} and his {@link PlayerProperty}
 * list. If the player has a property called "textures" then it will be converted to an instance of this class.
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerTexturesProperty extends PlayerProperty {

    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    @SerializedName("profileId")
    @Expose
    private String profileId;

    @SerializedName("profileName")
    @Expose
    private String profileName;

    @SerializedName("signatureRequired")
    @Expose
    private boolean signatureRequired;

    @SerializedName("SKIN")
    @Expose
    private PlayerSkinTexture skin;

    @SerializedName("CAPE")
    @Expose
    private PlayerCapeTexture cape;

    /**
     * Returns the skin texture object.
     * @return an instance of {@link PlayerSkinTexture}.
     * @since 1.0
     */
    public PlayerSkinTexture getSkin() {
        return skin;
    }

    /**
     * Sets the players skin object.
     * This will not modify anything on the Mojang account / API.
     * @param skin the skin texture.
     * @since 1.0
     */
    void setSkin(PlayerSkinTexture skin) {
        this.skin = skin;
    }

    /**
     * Returns the players cape texture.
     * @return an instance of {@link PlayerCapeTexture}.
     * @since 1.0
     */
    public PlayerCapeTexture getCape() {
        return cape;
    }

    /**
     * Sets the players skin texture.
     * This will not modify anything on the Mojang account / API.
     * @param cape the cape texture.
     */
    void setCape(PlayerCapeTexture cape) {
        this.cape = cape;
    }

    /**
     * Returns the timestamp, when the response was answered.
     * @return the timestamp.
     * @since 1.0
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the response.
     * This will not modify anything on the Mojang account / API.
     * @param timestamp the timestamp.
     * @since 1.0
     */
    void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the uuid of the player.
     * @return the uuid.
     * @since 1.0
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Sets the player uuid.
     * This will not modify anything on the Mojang account / API.
     * @param profileId the uuid.
     * @since 1.0
     */
    void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * Returns the players username.
     * @return the username.
     * @since 1.0
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Sets the players username.
     * This will not modify anything on the Mojang account / API.
     * @param profileName the username.
     * @since 1.0
     */
    void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Returns if a signature was requested or not.
     * @return true if a signature was requested, false if not.
     * @since 1.0
     */
    public boolean getSignatureRequired() {
        return signatureRequired;
    }

    /**
     * Sets if a signature was requested.
     * This will not modify anything on the Mojang account / API.
     * @param signatureRequired was the signature requested?
     * @since 1.0
     */
    void setSignatureRequired(boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

}
