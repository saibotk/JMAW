package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests regarding the {@link UUIDInfo} returned by the {@link MojangAPI#getUUIDsForUsernames(List)} method.
 *
 * @author saibotk
 */
public class UsernamesToUUIDInfoTest extends APITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getUUIDsForUsernames(List)} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("[\n" +
                "    {\n" +
                "        \"id\": \"b2732392fae140c3b836a066c6debd8f\",\n" +
                "        \"name\": \"Minecraft\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"069a79f444e94726a5befca90e38aaf5\",\n" +
                "        \"name\": \"Notch\"\n" +
                "    }\n" +
                "]"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        List<String> usernames = new ArrayList<>();
        usernames.add("Notch");
        usernames.add("Minecraft");

        List<UUIDInfo> info = null;
        try {
            info = classUnderTest.getUUIDsForUsernames(usernames);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("method should return an object.", info);
        assertEquals("b2732392fae140c3b836a066c6debd8f", info.get(0).getId());
        assertEquals("Minecraft", info.get(0).getName());
        assertEquals("069a79f444e94726a5befca90e38aaf5", info.get(1).getId());
        assertEquals("Notch", info.get(1).getName());
    }

    /**
     * Test the correct behavior by the {@link MojangAPI#getUUIDsForUsernames(List)} method, when the response contains
     * an error.
     */
    @Test(expected = ApiResponseException.class)
    public void testResponseOnError1() throws ApiResponseException {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "  \"error\": \"IllegalArgumentException\",\n" +
                "  \"errorMessage\": \"profileName can not be null or empty.\"\n" +
                "}").setResponseCode(400));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        List<String> usernames = new ArrayList<>();
        usernames.add("Notch");
        usernames.add("");

        classUnderTest.getUUIDsForUsernames(usernames);
    }
}

