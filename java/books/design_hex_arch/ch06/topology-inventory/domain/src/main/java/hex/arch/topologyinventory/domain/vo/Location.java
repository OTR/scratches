package hex.arch.topologyinventory.domain.vo;

import lombok.Builder;

@Builder
public record Location(
    String address,
    String city,
    String state,
    int zipCode,
    String country,
    float latitude,
    float longitude
) {}
