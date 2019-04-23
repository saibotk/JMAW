package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the players cape texture, found in {@link PlayerTexturesProperty#getCape()}.
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerCapeTexture {

    @SerializedName("url")
    @Expose
    private String url;

    /**
     * Returns the url to the cape texture.
     * @return the cape texture url.
     * @since 1.0
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the cape texture url.
     * This will not modify anything on the Mojang account / API.
     * @param url the url.
     * @since 1.0
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
