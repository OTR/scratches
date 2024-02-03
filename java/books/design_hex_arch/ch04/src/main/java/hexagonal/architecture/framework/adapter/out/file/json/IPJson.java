package hexagonal.architecture.framework.adapter.out.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class IPJson {

    @JsonProperty("address")
    private String address;

    @JsonProperty("protocol")
    private ProtocolJson protocol;

    private IPJson(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Null IP Address");
        }
        if (address.length() <= 15) {
            this.protocol = ProtocolJson.IPV4;
        } else {
            this.protocol = ProtocolJson.IPV6;
        }
        this.address = address;
    }

    public static IPJson fromAddress(String ipAddress) {
        return new IPJson(ipAddress);
    }

}
