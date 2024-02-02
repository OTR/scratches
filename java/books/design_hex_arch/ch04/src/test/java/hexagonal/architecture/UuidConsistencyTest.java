package hexagonal.architecture;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import java.util.Set;
import java.util.UUID;

public class UuidConsistencyTest {

    private static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance(
                    DigestProvider.SHA_256.getValue()
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public UuidConsistencyTest() {}

    public static void main(String[] args) {
        uuid_Values_Generated_From_Same_String__are_Equals();
    }

    public static void uuid_Values_Generated_From_Same_String__are_Equals() {
        // GIVEN
        MessageDigest digest = getDigest();
        byte[] input1 = digest.digest("Hello".getBytes());
        byte[] input2 = digest.digest("Hello".getBytes());

        // WHEN
        UUID actual1 = UUID.nameUUIDFromBytes(input1);
        UUID actual2 = UUID.nameUUIDFromBytes(input2);

        // THEN
        if(!actual1.equals(actual2)) {
            throw new AssertionError();
        }
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
