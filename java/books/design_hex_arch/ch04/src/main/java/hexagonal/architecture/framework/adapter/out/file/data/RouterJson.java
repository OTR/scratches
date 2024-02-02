package hexagonal.architecture.framework.adapter.out.file.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import hexagonal.architecture.domain.vo.RouterId;

import java.util.UUID;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RouterJson {

    @JsonProperty("routerId")
    private UUID routerId;

    @JsonProperty("routerType")
    private RouterTypeJson routerType;

    @JsonProperty("switch")
    private SwitchJson networkSwitch;

    public UUID getRouterId() {
        return this.routerId;
    }

}
