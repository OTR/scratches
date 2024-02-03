package hex.arch.topologyinventory.domain.vo;

import lombok.Getter;

@Getter
public class IP {

    private final String ipAddress;
    private final Protocol protocol;

    public IP(String ipAddress) {
        if (ipAddress == null) {
            throw new IllegalArgumentException("Null IP Address");
        }
        if (ipAddress.length() <= 15) {
            this.protocol = Protocol.IPV4;
        } else {
            this.protocol = Protocol.IPV6;
        }
        this.ipAddress = ipAddress;
    }

}
