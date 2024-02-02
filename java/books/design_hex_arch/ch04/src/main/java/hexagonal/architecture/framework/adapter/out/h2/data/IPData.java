package hexagonal.architecture.framework.adapter.out.h2.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class IPData {

    private String address;

    @Embedded
    @Enumerated(EnumType.STRING)
    private ProtocolData protocol;

    private IPData(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Null IP Address");
        }
        if (address.length() <= 15) {
            this.protocol = ProtocolData.IPV4;
        } else {
            this.protocol = ProtocolData.IPV6;
        }
        this.address = address;
    }

    public IPData() {}

    public static IPData fromAddress(String address) {
        return new IPData(address);
    }

}
