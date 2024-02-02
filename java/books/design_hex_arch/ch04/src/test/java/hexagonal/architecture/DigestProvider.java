package hexagonal.architecture;

public enum DigestProvider {

    SHA_1("SHA-1"),
    //    "MD2",
//    "MD5",
//    "SHA-512/256",
//    "SHA3-512",
    SHA_256("SHA-256");
//    "SHA-384",
//    "SHA-512/224",
//    "SHA-512",
//    "SHA3-256",
//    "SHA-224",
//    "SHA3-384",
//    "SHA3-224",

    private final String value;

    DigestProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
