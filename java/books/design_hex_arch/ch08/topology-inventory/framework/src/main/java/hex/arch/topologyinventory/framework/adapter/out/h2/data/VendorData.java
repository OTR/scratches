package hex.arch.topologyinventory.framework.adapter.out.h2.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum VendorData {
    CISCO,
    NETGEAR,
    HP,
    TPLINK,
    DLINK,
    JUNIPER
}
