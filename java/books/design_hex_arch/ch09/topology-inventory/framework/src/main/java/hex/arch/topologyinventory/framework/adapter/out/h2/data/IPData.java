package hex.arch.topologyinventory.framework.adapter.out.h2.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class IPData {

    private String address;

    @Embedded
    @Enumerated(value = EnumType.STRING)
    private ProtocolData protocol;

    public IPData() {}

    private IPData(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Null IP address");
        }
        if (address.length() <= 15) {
            this.protocol = ProtocolData.IPV4;
        } else {
            this.protocol = ProtocolData.IPV6;
        }
        this.address = address;
    }

    public static IPData fromAddress(String address) {
        return new IPData(address);
    }

    public String getAddress() {
        return address;
    }

    public ProtocolData getProtocol() {
        return protocol;
    }

}
