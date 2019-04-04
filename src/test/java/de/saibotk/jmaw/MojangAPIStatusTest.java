package de.saibotk.jmaw;

import de.saibotk.jmaw.models.MojangAPIStatus;
import de.saibotk.jmaw.models.MojangApiInterface;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests regarding the {@link MojangAPIStatus} returned by the {@link MojangAPI}.
 *
 * @author saibotk
 */
public class MojangAPIStatusTest extends MojangAPITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getAPIStatus()} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testAPIStatusResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("[{\"minecraft.net\":\"green\"},{\"session.minecraft.net\":\"green\"},{\"account.mojang.com\":\"green\"},{\"authserver.mojang.com\":\"green\"},{\"sessionserver.mojang.com\":\"green\"},{\"api.mojang.com\":\"green\"},{\"textures.minecraft.net\":\"green\"},{\"mojang.com\":\"green\"}]"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.statusAPI = getRetrofit(mockWebServer).create(MojangApiInterface.class);

        // execute
        MojangAPIStatus mas = classUnderTest.getAPIStatus();

        // expect
        assertNotNull("getAPIStatus() should return an object.", mas);
        assertSame(8, mas.getServices().size());
        mas.getServices().forEach((name, status) -> assertSame(MojangAPIStatus.MojangAPIStatusCode.GREEN, status));
    }


}
