package de.saibotk.jmaw;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests regarding the {@link MojangAPI#getBlockedServers()} method.
 *
 * @author saibotk
 */
public class BlockedServersTest extends APITest {

    /**
     * Test the correct deserialization by the {@link MojangAPI#getBlockedServers()} method.
     * This test will use valid data and expects a normal result.
     */
    @Test public void testBlockedServersResponseDeserialization1() {
        // before
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("26b07aaec4373bec32872fdb416959fa6d33a58a\n" +
                "72fd29f430c91c583bb7216fe673191dc25a7e18\n" +
                "e38e82a54b47c7c5394670bb34b3aa941219959b\n" +
                "d1bab7fcb1d44a0ad1084fb201006d79d05ae6e7\n" +
                "1822a17662c7e0cf3b815c257d32c2aa0245fad0\n" +
                "7905e1eeee5d57268bb9cbea2e0acbb5421a667b\n" +
                "56c7a4ccff309d6eb3c5737fe9509c3555e7f5fa\n" +
                "cf2f874a649da0118f717f7edb1f5fffcbae8c6b\n" +
                "c800614f07e155ca842e23f84c6a553973ccdb1f\n" +
                "04e8d3c2cb1b5bf79a02838b8560a9d01704120e\n" +
                "9907df5eaa1939143022b4fdaab45cb00947ae4a\n" +
                "fc752efc29e88d71e8cb42f4654921ca8eaa56b2\n" +
                "a6baf7d3ddfeae0ecb6c82f72771ab13f214ee5b\n" +
                "17ca13f0120441568b8b492bd8ed637c05a5e394\n" +
                "6071f6306688398d41a69f097c73d1762ce62eee\n" +
                "085eb6b3698fca114061d5dc45ea47562fa77fdc\n" +
                "cdf5108556c00e6e0c42e1a52516d1b4317bbc1c\n" +
                "84e810d48c25030dbaf2e63cc3dec61dfcc30992\n" +
                "33ff2a03cbba3ad9e7df62e3bd550f5a552a0940\n" +
                "696ebe386c8f7c6ed86d45598729219c9febb210\n" +
                "52b2356738de90f261266321bd6a4411f8ea41de\n" +
                "a7c9fef03df651678128611502698fd60490482e\n" +
                "62998dad2febe4809a7105230142022fe0923d4a\n" +
                "124c756cdb9dc8077f70558f0bca43e0f6bf887a\n" +
                "dc2c735b3e6aba51ece294d7de21b947379aac4d\n" +
                "c1f6e038e62cfaf0f8cf8d0929e8d716206f1585\n" +
                "780874bcade721dfccdf2ccd51cec3eb3a35c958\n" +
                "efed28c35a3bf3433c582397f238f824bf4494b3\n" +
                "ac7e715494da3b381bd37e87419c44e7d506f01b\n" +
                "e41ddcf7d4b37611b676701060eae90fa65f11d1\n" +
                "ab8724e0fac2ec56bda2f774dc5464bf0520da27\n" +
                "c8f090859ea7c0afdab8b22a842532b979235951\n" +
                "0097a2f9a3a32a6fa194f503662e225fabf705d5\n" +
                "91c6caa70860d687fa3d8db06bb17ce30172e7f3\n" +
                "567d80c36e84c5d2a8731babb338c293584f5a51\n" +
                "d062578440d49c485ff01d5dc728b09a7f3d7295\n" +
                "dd33113fb4e0e524b79b6b0765732972b95f5761\n" +
                "3a3a226a1fd8f6c7890874cbbb64876dc42ef15f\n" +
                "09277388537e05ded62f6aedc742b23ba42f3858\n" +
                "af4dc4c650af76116eab48b26d6618537c69acee\n" +
                "70f8fec738105a38cdee6a3fdc1339bfe206d03c\n" +
                "4da16c71bc880c7503efef266a01eff86a2c6d4a\n" +
                "408b38cf72bb1ccedab398ab6a6b38fe8fc3812d\n" +
                "5f50417af25d94f60d2ee84935a547e80d0d1c3c\n" +
                "53775186bdc0501519ad217b09f46cb3e0eb4e91\n" +
                "c2210bcf40231cc8bfa394ed92d6cbf63aec1169\n" +
                "c5d268d73ef2c250633ec8826ec73024a46b6739\n" +
                "c3fb01df7f319445374a6e0c18fc01b8cc954a26\n" +
                "08a809ce63698b07399b52c444eb8eef3dc8ddca\n" +
                "c034f98dd19a64214e3deb8909842eafd4e62c5c\n" +
                "a99576a47aeb5bfb26fcc2ca7be1e8cc69a288ba\n" +
                "92eafd6322c802a6ef5ceb64fe8f1f3cd67b2c13"));

        MojangAPI classUnderTest = new MojangAPI();
        classUnderTest.sessionAPILineReadInterface = getRetrofit(mockWebServer, Util.getStringLineRetrofitBuilder()).create(ApiInterface.class);

        // execute
        List<String> mas = null;
        try {
            mas = classUnderTest.getBlockedServers().orElse(null);
        } catch (ApiResponseException e) {
            e.printStackTrace();
        }

        // expect
        assertNotNull("getBlockedServers() should return an object.", mas);
        assertSame(52, mas.size());
    }

}
