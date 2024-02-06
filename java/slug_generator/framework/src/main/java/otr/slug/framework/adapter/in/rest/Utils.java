package otr.slug.framework.adapter.in.rest;

public class Utils {

    public static void printGreetingsLine() {
        System.out.println(getServerGreetings());
    }

    private static String getServerGreetings() {
        return "REST endpoint listening on port 8080...\n" +
            "http://localhost:8080/create?user_input=hello%20World";
    }

}
