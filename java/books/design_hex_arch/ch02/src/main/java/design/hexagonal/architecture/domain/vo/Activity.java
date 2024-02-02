package design.hexagonal.architecture.domain.vo;

public class Activity {

    private final String srcHost;
    private final String dstHost;

    public Activity(String srcHost, String dstHost) {
        this.srcHost = srcHost;
        this.dstHost = dstHost;
    }

    public String getSrcHost() {
        return srcHost;
    }

    public String getDstHost() {
        return dstHost;
    }

}
