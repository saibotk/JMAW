package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

/**
 * This models the players skin texture object found in {@link PlayerTexturesProperty#getSkin()}.
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerSkinTexture {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("metadata")
    @Expose
    private SkinMetadata metadata;

    /**
     * Returns the url to the skin texture.
     * @return the skins url.
     * @since 1.0
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the skin texture url.
     * This will not modify anything on the Mojang account / API.
     * @param url the skin's url.
     * @since 1.0
     */
    void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the {@link SkinMetadata} if there is one.
     * Usually this is only the case where the slim model was used (Alex style), otherwise it will be omitted (null will
     * be returned).
     * @return an instance of {@link SkinMetadata} or null if the response did not include any information.
     * @since 1.0
     */
    public Optional<SkinMetadata> getMetadata() {
        return Optional.ofNullable(metadata);
    }

    /**
     * Sets the skins metadata.
     * This will not modify anything on the Mojang account / API.
     * @param metadata the metadata.
     * @since 1.0
     */
    void setMetadata(SkinMetadata metadata) {
        this.metadata = metadata;
    }
}
