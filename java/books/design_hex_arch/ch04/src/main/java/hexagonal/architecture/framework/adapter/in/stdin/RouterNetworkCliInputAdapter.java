package hexagonal.architecture.framework.adapter.in.stdin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.framework.adapter.in.RouterNetworkInputAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RouterNetworkCliInputAdapter extends RouterNetworkInputAdapter {

    public RouterNetworkCliInputAdapter(RouterNetworkUseCase useCase) {
        this.routerNetworkUseCase = useCase;
    }

    @Override
    public Router processRequest(Object requestParams) {
        Map<String, String> params = stdinParams(requestParams);
        this.router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String routerJson = mapper.writeValueAsString(
                RouterJsonFileMapper.toJson(this.router)
            );
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return this.router;
    }

    private Map<String, String> stdinParams(Object requestParams) {
        Map<String, String> params = new HashMap<>();

        if (requestParams instanceof Scanner) {
            Scanner scanner = (Scanner) requestParams;

            System.out.println("Please inform the Router ID:");
            var routerId = scanner.nextLine();
            params.put("routerId", routerId);

            System.out.println("Please inform the IP address:");
            var address = scanner.nextLine();
            params.put("address", address);

            System.out.println("Please inform the Network Name:");
            var name = scanner.nextLine();
            params.put("name", name);

            System.out.println("Please inform the CIDR:");
            var cidr = scanner.nextLine();
            params.put("cidr", cidr);
        } else {
            throw new IllegalArgumentException(
                "Request with invalid parameters"
            );
        }
        return params;
    }

}
