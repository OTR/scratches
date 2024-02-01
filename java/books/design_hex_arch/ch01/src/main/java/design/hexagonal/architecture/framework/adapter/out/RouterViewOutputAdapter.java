package design.hexagonal.architecture.framework.adapter.out;

import design.hexagonal.architecture.application.port.out.RouterViewOutputPort;

public class RouterViewFileOutputAdapter implements RouterViewOutputPort {

    @Override
    public List<Router> fetchRouters() {
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();

        try(
            Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                    Objects.requireNonNull(
                        RouterViewFileOutputAdapter
                                .class
                                .getClassLoader()
                                .getResourceAsStream
                                .get("routers")
                    )
                )
            ).lines()
        ) {
            stream.forEach(line -> {
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(
                    RouterType.valueOf(type), RouterId.of(id)
                );
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routers;
    }

}
