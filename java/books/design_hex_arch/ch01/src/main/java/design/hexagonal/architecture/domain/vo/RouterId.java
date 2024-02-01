package design.hexagonal.architecture.domain.vo;

import design.hexagonal.architecture.domain.exception.NotImplementedYet;

import java.util.UUID;

public class RouterId {

    private final UUID id;

    private RouterId(UUID id) {
        this.id = id;
    }

    public static RouterId withId(String id) {
        return new RouterId(UUID.fromString(id));
    }

    public static RouterId withoutId() {
        return new RouterId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id=" + id +
                "}";
    }

}
