package de.saibotk.jmaw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This models the response by {@link MojangAPI#getSaleStatistics()}.
 *
 * @author saibotk
 * @since 1.0
 */
public class SaleStatistics {

    public enum MetricKeys {
        ITEM_SOLD_MINECRAFT,
        PREPAID_CARD_REDEEMED_MINECRAFT,
        ITEM_SOLD_COBALT,
        ITEM_SOLD_SCROLLS
    }

    @SerializedName("total")
    @Expose
    private long total;

    @SerializedName("last24h")
    @Expose
    private long last24h;

    @SerializedName("saleVelocityPerSeconds")
    @Expose
    private float saleVelocityPerSeconds;

    /**
     * Returns the total amount.
     * @return total amount.
     * @since 1.0
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets the total amount.
     * This will not modify anything on the Mojang account / API.
     * @param total total amount.
     * @since 1.0
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Returns the amount in the last 24 hours.
     * @return amount (last 24 hours).
     * @since 1.0
     */
    public long getLast24h() {
        return last24h;
    }

    /**
     * Sets the amount for the last 24 hours.
     * This will not modify anything on the Mojang account / API.
     * @param last24h the amount for the last 24 hours.
     * @since 1.0
     */
    public void setLast24h(long last24h) {
        this.last24h = last24h;
    }

    /**
     * Returns the sale velocity per second.
     * @return the sale velocity / s.
     * @since 1.0
     */
    public float getSaleVelocityPerSeconds() {
        return saleVelocityPerSeconds;
    }

    /**
     * Sets the sale velocity per seconds.
     * This will not modify anything on the Mojang account / API.
     * @param saleVelocityPerSeconds the sale velocity / s.
     * @since 1.0
     */
    public void setSaleVelocityPerSeconds(float saleVelocityPerSeconds) {
        this.saleVelocityPerSeconds = saleVelocityPerSeconds;
    }

}