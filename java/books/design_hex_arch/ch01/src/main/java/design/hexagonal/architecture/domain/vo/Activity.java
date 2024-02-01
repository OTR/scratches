package design.hexagonal.architecture.domain.vo;

public class Activity {

    private final String description;
    private final String srcHost;
    private final String dstHost;

    public Activity(String description, String srcHost, String dstHost) {
        this.description = description;
        this.srcHost = description.split(">")[0];
        this.dstHost = description.split(">")[1];
    }

    public String getDescription() {
        return description;
    }

    public String getSrcHost() {
        return srcHost;
    }

    public String getDstHost() {
        return dstHost;
    }

}
