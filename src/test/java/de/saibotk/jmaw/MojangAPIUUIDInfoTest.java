package de.saibotk.jmaw;

import de.saibotk.jmaw.models.MojangAPIUUIDInfo;
import de.saibotk.jmaw.models.MojangApiInterface;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests regarding the {@link MojangAPIUUIDInfo} returned by the {@link MojangAPI#getUUIDInfo(String, long)} method.
 *
 * @author saibotk
 */
public class MojangAPIUUIDInfoTest extends MojangAPITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getUUIDInfo(String)} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testUUIDInfoResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":\"069a79f444e94726a5befca90e38aaf5\",\"name\":\"Notch\"}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(MojangApiInterface.class);

        // execute
        MojangAPIUUIDInfo info = null;
        try {
            info = classUnderTest.getUUIDInfo("Notch");
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getUUIDInfo() should return an object.", info);
        assertEquals("069a79f444e94726a5befca90e38aaf5", info.getId());
        assertEquals("Notch", info.getName());
    }

    /**
     * Test the correct behavior by the {@link MojangAPI#getUUIDInfo(String)} method, when the response contains
     * an error.
     */
    @Test(expected = ApiResponseException.class)
    public void testUUIDInfoResponseOnError1() throws ApiResponseException {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "  \"error\": \"IllegalArgumentException\",\n" +
                "  \"errorMessage\": \"Invalid timestamp.\"\n" +
                "}").setResponseCode(400));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(MojangApiInterface.class);

        // execute
        classUnderTest.getUUIDInfo("Notch");
    }
}

