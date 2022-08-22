package duplenskikh.server;

public class Request {
    private final String route;
    private final String method;

    public Request(String route, String method) {
        this.route = route;
        this.method = method;
    }

    public String getRoute() {
        return route;
    }

    public String getMethod() {
        return method;
    }
}
