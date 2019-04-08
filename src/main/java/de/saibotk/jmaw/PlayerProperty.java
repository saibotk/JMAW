package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the basic player property found in the list of all properties a player has
 * ({@link PlayerProfile#getProperties()}). Usually not returned by the Mojang API, only the "textures" property is
 * known to be returned, which will be converted automaticially to a {@link PlayerTexturesProperty}, a subclass to this.
 * The rawValue field is mostly encoded in base64, which is automatically decoded for the
 * {@link PlayerTexturesProperty}.
 *
 * @author saibotk
 * @since 1.0
 */
public class PlayerProperty {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("value")
    @Expose
    private String rawValue;

    @SerializedName("signature")
    @Expose
    private String signature;

    /**
     * Returns the property's name.
     * @return the name.
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the property's name.
     * This will not modify anything on the Mojang account / API.
     * @param name the name.
     * @since 1.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the raw value, that was returned by the API. Usually encoded as base64.
     * @return the encoded raw value returned by the API.
     * @since 1.0
     */
    public String getRawValue() {
        return rawValue;
    }

    /**
     * Sets the raw value.
     * This will not modify anything on the Mojang account / API.
     * @param value the encoded value.
     * @since 1.0
     */
    public void setRawValue(String value) {
        this.rawValue = value;
    }

    /**
     * Returns the base64 signature for the property, if requested.
     * @return the signature or <code>null</code> if not requested.
     * @since 1.0
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets the signature for the property.
     * This will not modify anything on the Mojang account / API.
     * @param signature the signature.
     * @since 1.0
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

}