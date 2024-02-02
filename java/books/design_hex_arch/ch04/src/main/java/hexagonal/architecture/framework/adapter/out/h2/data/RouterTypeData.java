package hexagonal.architecture.framework.adapter.out.h2.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RouterTypeData {
    EDGE,
    CORE;
}
