package duplenskikh.server.requestHandlers;

import java.io.OutputStream;
import java.io.Reader;

public interface RequestHandler {
    String getRoute();

    String getMethod();

    void handleRequest(OutputStream outputStream, Reader reader);
}
