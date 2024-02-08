package hex.arch.topologyinventory.framework.adapter.out.h2.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum ProtocolData {
    IPV4,
    IPV6;
}