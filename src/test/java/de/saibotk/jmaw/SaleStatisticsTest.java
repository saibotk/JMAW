package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests regarding the {@link SaleStatistics} returned by the {@link MojangAPI#getSaleStatistics(List)} method.
 *
 * @author saibotk
 */
public class SaleStatisticsTest extends APITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getSaleStatistics(List)} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testSaleStatisticsResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "    \"total\": 30086047,\n" +
                "    \"last24h\": 5704,\n" +
                "    \"saleVelocityPerSeconds\": 0.046666667\n" +
                "}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);
        List<SaleStatistics.MetricKeys> metricKeys = new ArrayList<>();

        metricKeys.add(SaleStatistics.MetricKeys.ITEM_SOLD_MINECRAFT);
        metricKeys.add(SaleStatistics.MetricKeys.PREPAID_CARD_REDEEMED_MINECRAFT);

        // execute
        SaleStatistics mas = null;
        try {
            mas = classUnderTest.getSaleStatistics(metricKeys);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getSaleStatistics() should return an object.", mas);
        assertEquals(30086047L, mas.getTotal());
        assertEquals(5704, mas.getLast24h());
        assertEquals(0.046666667f, mas.getSaleVelocityPerSeconds(), 0.0000000001);
    }

    /**
     * Test the correct deserialization by the {@link MojangAPI#getSaleStatistics(List)} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testSaleStatisticsResponseDeserialization2() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "    \"total\": 30260034,\n" +
                "    \"last24h\": 5704,\n" +
                "    \"saleVelocityPerSeconds\": 0.04\n" +
                "}"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.mojangAPIInterface = getRetrofit(mockWebServer).create(ApiInterface.class);
        List<SaleStatistics.MetricKeys> metricKeys = new ArrayList<>();

        metricKeys.add(SaleStatistics.MetricKeys.ITEM_SOLD_MINECRAFT);
        metricKeys.add(SaleStatistics.MetricKeys.PREPAID_CARD_REDEEMED_MINECRAFT);
        metricKeys.add(SaleStatistics.MetricKeys.ITEM_SOLD_COBALT);
        metricKeys.add(SaleStatistics.MetricKeys.ITEM_SOLD_SCROLLS);

        // execute
        SaleStatistics mas = null;
        try {
            mas = classUnderTest.getSaleStatistics(metricKeys);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getSaleStatistics() should return an object.", mas);
        assertEquals(30260034L, mas.getTotal());
        assertEquals(5704, mas.getLast24h());
        assertEquals(0.04f, mas.getSaleVelocityPerSeconds(), 0.0000000001);
    }

}