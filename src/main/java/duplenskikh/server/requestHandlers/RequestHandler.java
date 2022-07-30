package duplenskikh.server.requestHandlers;

import java.io.OutputStream;

public interface RequestHandler {
    String getRoute();
    void handleRequest(OutputStream outputStream);
}
