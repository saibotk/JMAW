package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests regarding the {@link APIStatus} returned by the {@link MojangAPI#getAPIStatus()} method.
 *
 * @author saibotk
 */
public class APIStatusTest extends APITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getAPIStatus()} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testAPIStatusResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("[{\"minecraft.net\":\"green\"},{\"session.minecraft.net\":\"green\"},{\"account.mojang.com\":\"green\"},{\"authserver.mojang.com\":\"green\"},{\"sessionserver.mojang.com\":\"green\"},{\"api.mojang.com\":\"green\"},{\"textures.minecraft.net\":\"green\"},{\"mojang.com\":\"green\"}]"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.statusAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        APIStatus mas = null;
        try {
            mas = classUnderTest.getAPIStatus().orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getAPIStatus() should return an object.", mas);
        assertSame(8, mas.getServices().size());
        mas.getServices().forEach((name, status) -> assertSame(APIStatus.MojangAPIStatusCode.GREEN, status));
    }

    /**
     * Test the correct deserialization by the {@link MojangAPI#getAPIStatus()} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testAPIStatusResponseDeserialization2() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("[{\"minecraft.net\":\"yellow\"},{\"session.minecraft.net\":\"red\"},{\"account.mojang.com\":\"green\"}]"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.statusAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        APIStatus mas = null;
        try {
            mas = classUnderTest.getAPIStatus().orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getAPIStatus() should return an object.", mas);
        assertSame(3, mas.getServices().size());
        assertEquals(APIStatus.MojangAPIStatusCode.YELLOW, mas.get("minecraft.net").orElse(null));
        assertEquals(APIStatus.MojangAPIStatusCode.RED, mas.get("session.minecraft.net").orElse(null));
        assertEquals(APIStatus.MojangAPIStatusCode.GREEN, mas.get("account.mojang.com").orElse(null));
    }

}
