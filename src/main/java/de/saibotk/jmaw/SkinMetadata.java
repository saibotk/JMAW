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
    public enum SkinModel {
        @SerializedName("") DEFAULT(""),
        @SerializedName("slim") SLIM ("slim");

        private final String usage;

        SkinModel(String usage) {
            this.usage = usage;
        }

        @Override
        public String toString() {
            return this.usage;
        }
    }

    @SerializedName("model")
    @Expose
    private SkinModel model;

    /**
     * Returns the model. Mostly just "slim" otherwise it is interpreted as Steve's style.
     * @return the model.
     * @since 1.0
     */
    public SkinModel getModel() {
        return model;
    }

    /**
     * Sets the model.
     * This will not modify anything on the Mojang account / API.
     * @param model the model that is used.
     * @since 1.0
     */
    public void setModel(SkinModel model) {
        this.model = model;
    }
}
