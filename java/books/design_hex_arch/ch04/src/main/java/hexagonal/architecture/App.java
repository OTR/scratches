package hexagonal.architecture;

import com.sun.net.httpserver.HttpServer;

import hexagonal.architecture.application.port.in.RouterNetworkInputPort;
import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.framework.adapter.in.RouterNetworkCliInputAdapter;
import hexagonal.architecture.framework.adapter.in.RouterNetworkInputAdapter;
import hexagonal.architecture.framework.adapter.in.RouterNetworkRestInputAdapter;
import hexagonal.architecture.framework.adapter.in.RouterViewCliInputAdapter;
import hexagonal.architecture.framework.adapter.out.file.RouterNetworkFileOutputAdapter;
import hexagonal.architecture.framework.adapter.out.h2.RouterNetworkH2OutputAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    private RouterNetworkOutputPort outputPort;
    private RouterNetworkUseCase useCase;
    private RouterNetworkInputAdapter inputAdapter;

    void setAdapter(String adapter) {
        switch (adapter) {
            case "rest" -> {
                this.outputPort = RouterNetworkH2OutputAdapter.getInstance();
                this.useCase = new RouterNetworkInputPort(this.outputPort);
                this.inputAdapter = new RouterNetworkRestInputAdapter(
                        this.useCase
                );
                rest();
            }
            default -> {
                this.outputPort = RouterNetworkFileOutputAdapter.getInstance();
                this.useCase = new RouterNetworkInputPort(this.outputPort);
                this.inputAdapter = new RouterNetworkCliInputAdapter(
                        this.useCase
                );
                cli();
            }
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        this.inputAdapter.processRequest(scanner);
    }

    private void rest() {
        try {
            System.out.println("REST endpoint listening on port 8080...");
            HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(8080), 0
            );
            this.inputAdapter.processRequest(httpServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var cli = new RouterViewCliInputAdapter();
        String type = "CORE";
        List<Router> routers = cli.obtainRelatedRouters(type);
        System.out.println(
                routers.stream()
                    .map(Router::toString)
                    .collect(Collectors.joining("\n"))
        );
    }

}
