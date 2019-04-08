package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the players textures object in the {@link PlayerTexturesProperty}. It stores the skin and the cape
 * texture (if the player has a cape/custom skin otherwise <code>null</code> is returned).
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerTextures {

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
    public PlayerSkinTexture getSKIN() {
        return skin;
    }

    /**
     * Sets the players skin object.
     * This will not modify anything on the Mojang account / API.
     * @param skin the skin texture.
     * @since 1.0
     */
    public void setSKIN(PlayerSkinTexture skin) {
        this.skin = skin;
    }

    /**
     * Returns the players cape texture.
     * @return an instance of {@link PlayerCapeTexture}.
     * @since 1.0
     */
    public PlayerCapeTexture getCAPE() {
        return cape;
    }

    /**
     * Sets the players skin texture.
     * This will not modify anything on the Mojang account / API.
     * @param cape the cape texture.
     */
    public void setCAPE(PlayerCapeTexture cape) {
        this.cape = cape;
    }

}