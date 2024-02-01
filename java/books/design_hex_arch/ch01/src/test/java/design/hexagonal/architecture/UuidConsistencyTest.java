package design.hexagonal.architecture;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Set;
import java.util.UUID;
import java.security.MessageDigest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UuidConsistencyTest {

    private MessageDigest digest = MessageDigest.getInstance(
            DigestProvider.SHA_256.getValue()
    );

    public UuidConsistencyTest() throws NoSuchAlgorithmException {}

    @Test
    public void uuid_Values_Generated_From_Same_String__are_Equals() {
        // GIVEN
        byte[] input1 = digest.digest("Hello".getBytes());
        byte[] input2 = digest.digest("Hello".getBytes());

        // WHEN
        UUID actual1 = UUID.nameUUIDFromBytes(input1);
        UUID actual2 = UUID.nameUUIDFromBytes(input2);

        // THEN
        assertThat(actual1, equalTo(actual2));
    }

    private void listAvailableDigestProviders() {
        for (Provider provider : Security.getProviders()) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("MessageDigest")) {
                    System.out.println(
                            provider.getName() +
                            ": " + service.getAlgorithm()
                    );
                }
            }
        }
    }

}
