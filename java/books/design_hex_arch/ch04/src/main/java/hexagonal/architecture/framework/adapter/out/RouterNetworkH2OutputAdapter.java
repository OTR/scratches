package hexagonal.architecture.framework.adapter.out;

public class RouterNetworkH2OutputAdapter extends RouterNetworkOutputAdapter {

    private static RouterNetworkH2OutputAdapter instance;

    public static RouterNetworkH2OutputAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2OutputAdapter();
        }
        return instance;
    }

}
