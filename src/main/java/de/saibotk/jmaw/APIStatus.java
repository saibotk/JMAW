package de.saibotk.jmaw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is the response of the Mojang API containing a status signal {@link MojangAPIStatusCode}
 * for each service they offer.
 *
 * @author saibotk
 * @since 1.0
 */
public class APIStatus {

    /**
     * This is the status code returned by the API as an <code>enum</code>.
     * @since 1.0
     */
    public enum MojangAPIStatusCode { GREEN, YELLOW, RED }

    private Map<String, MojangAPIStatusCode> services = new HashMap<>();

    /**
     * This function returns a {@link Map} of all services with their name and corresponding status.
     * @return {@link HashMap}
     * @since 1.0
     */
    public Map<String, MojangAPIStatusCode> getServices() {
        return services;
    }

    /**
     * Sets the services map for this instance.
     * This will not modify anything on the Mojang account / API.
     * @param services the {@link Map} containing the services names and their corresponding status
     * @since 1.0
     */
    void setServices(Map<String, MojangAPIStatusCode> services) {
        this.services = services;
    }

    /**
     * Simple helper which calls {@link Map#getOrDefault(Object, Object)} on the internal services list.
     * @param serviceName the service.
     * @return the status for the service or null if the service was not found. (Wrapped in {@link Optional})
     * @since 1.0
     */
    public Optional<MojangAPIStatusCode> get(String serviceName) {
        return Optional.ofNullable(services.getOrDefault(serviceName, null));
    }
}