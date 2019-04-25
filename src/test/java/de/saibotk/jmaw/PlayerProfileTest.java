package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Tests regarding the {@link PlayerProfile} returned by the {@link MojangAPI#getPlayerProfile(String)} method.
 *
 * @author saibotk
 */
public class PlayerProfileTest extends APITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getPlayerProfile(String)} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testPlayerProfileResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":\"4566e69fc90748ee8d71d7ba5aa00d20\",\"name\":\"Thinkofdeath\",\"properties\":[{\"name\":\"textures\",\"value\":\"eyJ0aW1lc3RhbXAiOjE1NTQ2NDIxMzM4NDgsInByb2ZpbGVJZCI6IjQ1NjZlNjlmYzkwNzQ4ZWU4ZDcxZDdiYTVhYTAwZDIwIiwicHJvZmlsZU5hbWUiOiJUaGlua29mZGVhdGgiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRkMWUwOGIwYmI3ZTlmNTkwYWYyNzc1ODEyNWJiZWQxNzc4YWM2Y2VmNzI5YWVkZmNiOTYxM2U5OTExYWU3NSJ9LCJDQVBFIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjYzA4ODQwNzAwNDQ3MzIyZDk1M2EwMmI5NjVmMWQ2NWExM2E2MDNiZjY0YjE3YzgwM2MyMTQ0NmZlMTYzNSJ9fX0=\"}]}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        PlayerProfile pp = null;
        try {
            pp = classUnderTest.getPlayerProfile("4566e69fc90748ee8d71d7ba5aa00d20").orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull(pp);
        assertEquals("4566e69fc90748ee8d71d7ba5aa00d20", pp.getId());
        assertEquals("Thinkofdeath", pp.getUsername());
        assertNotNull(pp.getProperties());
        assertEquals(1, pp.getProperties().size());
        PlayerProperty textures = pp.getProperties().get(0);
        assertTrue(textures instanceof PlayerTexturesProperty);
        PlayerTexturesProperty ptp = (PlayerTexturesProperty) textures;
        assertEquals(Optional.empty(), ptp.getSignature());
        assertNotNull(ptp.getSkin().orElse(null));
        assertEquals("http://textures.minecraft.net/texture/74d1e08b0bb7e9f590af27758125bbed1778ac6cef729aedfcb9613e9911ae75", ptp.getSkin().get().getUrl());
        assertNotNull(ptp.getCape().orElse(null));
        assertEquals("http://textures.minecraft.net/texture/b0cc08840700447322d953a02b965f1d65a13a603bf64b17c803c21446fe1635", ptp.getCape().get().getUrl());
        assertEquals((Long) 1554642133848L, ptp.getTimestamp());
        assertFalse(ptp.getSignatureRequired());
    }

    /**
     * Test the correct deserialization by the {@link MojangAPI#getPlayerProfile(String)} method.
     * This test will use valid data and expects a normal result.
     * Also this will use multiple properties, which is usually not returned by the API, but should work with our
     * Wrapper.
     */
    @Test public void testPlayerProfileResponseDeserialization2() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":\"c9b54008fd8047428b238787b5f2401c\",\"name\":\"MinecraftChick\",\"properties\":[{\"name\":\"textures\",\"value\":\"eyJ0aW1lc3RhbXAiOjE1NTU0MzM4MjA1MzcsInByb2ZpbGVJZCI6ImM5YjU0MDA4ZmQ4MDQ3NDI4YjIzODc4N2I1ZjI0MDFjIiwicHJvZmlsZU5hbWUiOiJNaW5lY3JhZnRDaGljayIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kODJlMGYwNzIyYjgwNjg5NTY4ODI4NGMzMTY2NTg1ZjdiYjliZmZjNzE4ZTZmN2Y0YmRjYzczYzJiMjNhZmUwIiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=\"}]}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        PlayerProfile pp = null;
        try {
            pp = classUnderTest.getPlayerProfile("c9b54008fd8047428b238787b5f2401c").orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull(pp);
        assertEquals("c9b54008fd8047428b238787b5f2401c", pp.getId());
        assertEquals("MinecraftChick", pp.getUsername());
        assertNotNull(pp.getProperties());
        assertEquals(1, pp.getProperties().size());
        PlayerProperty textures = pp.getProperties().get(0);
        assertTrue(textures instanceof PlayerTexturesProperty);
        PlayerTexturesProperty ptp = (PlayerTexturesProperty) textures;
        assertEquals(Optional.empty(), ptp.getSignature());
        assertNotNull(ptp.getSkin().orElse(null));
        assertEquals("http://textures.minecraft.net/texture/d82e0f0722b806895688284c3166585f7bb9bffc718e6f7f4bdcc73c2b23afe0", ptp.getSkin().get().getUrl());
        assertEquals(Optional.empty(), ptp.getCape());
        assertEquals((Long) 1555433820537L, ptp.getTimestamp());
        assertNotNull(ptp.getSkin().get().getMetadata().orElse(null));
        assertEquals(SkinMetadata.SkinModel.SLIM, ptp.getSkin().get().getMetadata().get().getModel());
        assertFalse(ptp.getSignatureRequired());
    }

    /**
     * Test the correct deserialization by the {@link MojangAPI#getPlayerProfile(String)} method.
     * This test will use valid data and expects a normal result.
     * Also this will not contain a textures property, which is usually not returned by the API, but should work with
     * our Wrapper.
     */
    @Test public void testPlayerProfileResponseDeserialization3() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":\"4566e69fc90748ee8d71d7ba5aa00d20\",\"name\":\"Thinkofdeath\",\"properties\":[{\"name\":\"test1\",\"value\":\"testdata1\"},{\"name\":\"test2\",\"value\":\"testdata2\"}]}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        PlayerProfile pp = null;
        try {
            pp = classUnderTest.getPlayerProfile("4566e69fc90748ee8d71d7ba5aa00d20").orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull(pp);
        assertEquals("4566e69fc90748ee8d71d7ba5aa00d20", pp.getId());
        assertEquals("Thinkofdeath", pp.getUsername());
        assertNotNull(pp.getProperties());
        assertEquals(2, pp.getProperties().size());
        PlayerProperty pp1 = pp.getProperties().get(0);
        assertFalse(pp1 instanceof PlayerTexturesProperty);
        assertEquals(Optional.empty(), pp1.getSignature());
        assertEquals("test1", pp1.getName());
        assertEquals("testdata1", pp1.getRawValue());
        PlayerProperty pp2 = pp.getProperties().get(1);
        assertFalse(pp2 instanceof PlayerTexturesProperty);
        assertEquals(Optional.empty(), pp2.getSignature());
        assertEquals("test2", pp2.getName());
        assertEquals("testdata2", pp2.getRawValue());
    }

    /**
     * Test the correct deserialization by the {@link MojangAPI#getPlayerProfile(String)} method.
     * This test will use valid data and expects a normal result.
     * Also this will contain an empty textures property.
     *
     */
    @Test public void testPlayerProfileResponseDeserialization4() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\"id\":\"ec561538f3fd461daff5086b22154bce\",\"name\":\"Alex\",\"properties\":[{\"name\":\"textures\",\"value\":\"eyJ0aW1lc3RhbXAiOjE1NTU0MzE5NzU1OTAsInByb2ZpbGVJZCI6ImVjNTYxNTM4ZjNmZDQ2MWRhZmY1MDg2YjIyMTU0YmNlIiwicHJvZmlsZU5hbWUiOiJBbGV4IiwidGV4dHVyZXMiOnt9fQ==\"}]}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        PlayerProfile pp = null;
        try {
            pp = classUnderTest.getPlayerProfile("ec561538f3fd461daff5086b22154bce").orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull(pp);
        assertEquals("ec561538f3fd461daff5086b22154bce", pp.getId());
        assertEquals("Alex", pp.getUsername());
        assertNotNull(pp.getProperties());
        assertEquals(1, pp.getProperties().size());
        assertNotNull(pp.getProperties().get(0));
        assertEquals(Optional.empty(), ((PlayerTexturesProperty) pp.getProperties().get(0)).getSkin());
        assertEquals(Optional.empty(), ((PlayerTexturesProperty) pp.getProperties().get(0)).getCape());
    }

    /**
     * Test the correct behavior by the {@link MojangAPI#getPlayerProfile(String)} method, when the response contains
     * no content.
     */
    @Test public void testPlayerProfileResponseOnNoContent() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(204));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        PlayerProfile pp = null;
        try {
            pp = classUnderTest.getPlayerProfile("4566e69fc90748ee8d71d7ba5aa00d20").orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        assertNull(pp);
    }

    /**
     * Test the correct behavior by the {@link MojangAPI#getPlayerProfile(String)} method, when the response contains
     * an error.
     */
    @Test(expected = ApiResponseException.class)
    public void testPlayerProfileResponseOnError() throws ApiResponseException {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(500));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);

        // execute
        classUnderTest.getPlayerProfile("4566e69fc90748ee8d71d7ba5aa00d20");
    }
}