package design.hexagonal.architecture.domain.vo;

public record Network(
        IP address,
        String name,
        int cird
) {

    public Network {
        if (cird < 1 || cird > 32) {
            throw new IllegalArgumentException("Invalid CIDR value");
        }
    }

}
