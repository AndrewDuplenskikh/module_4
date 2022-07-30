package duplenskikh.server.requestHandlers;

public interface RequestHandler {
    String getRoute();
    void handleRequest();
}
