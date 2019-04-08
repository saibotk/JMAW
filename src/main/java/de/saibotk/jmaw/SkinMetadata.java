package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the metadata object returned by {@link PlayerSkinTexture#getMetadata()} method. It contains information
 * about the model that was used. This will be set if the player model has slim arms ( value = "slim" -> Alex style ).
 * Otherwise the API usually does not return a metadata property, so the model has the default Steve style.
 *
 * @author saibotk
 * @since 1.0
 */
public class SkinMetadata {

    @SerializedName("model")
    @Expose
    private String model;

    /**
     * Returns the model string. Mostly just "slim" otherwise not set and is interpreted as Steve's style.
     * @return the model string.
     * @since 1.0
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model string.
     * This will not modify anything on the Mojang account / API.
     * @param model the model that is used.
     * @since 1.0
     */
    public void setModel(String model) {
        this.model = model;
    }
}
