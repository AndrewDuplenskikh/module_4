package duplenskikh.server;

import duplenskikh.server.requestHandlers.ErrorRequestHandler;
import duplenskikh.server.requestHandlers.FormRequestHandler;
import duplenskikh.server.requestHandlers.MyRequestHandler;
import duplenskikh.server.requestHandlers.RequestHandler;

import java.io.*;
import java.util.ArrayList;

public class Router {
    // TODO arrayList => map
    private final ArrayList<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();
    private final BufferedReader input;
    private final OutputStream outputStream;
    private final RequestHandler errorRequestHandler = new ErrorRequestHandler(new Request("/error", "GET"), "error.html");

    public Router(InputStream inputStream, OutputStream outputStream) {
        input = new BufferedReader(new InputStreamReader(inputStream));
        this.outputStream = outputStream;
        requestHandlers.add(new MyRequestHandler(new Request("/", "GET"), "index.html"));
        requestHandlers.add(new MyRequestHandler(new Request("/another", "GET"), "another.html"));
        requestHandlers.add(new FormRequestHandler(new Request("/post-data", "POST")));
    }

    public void listen() {
        Request request = getRequestData();
        RequestHandler requestHandler = findRequestHandler(request);
        requestHandler.handleRequest(outputStream, input);
    }

    private Request getRequestData() {
        try {
            String inputData = input.readLine();
            if (inputData == null) {
                return null;
            }
            String[] arr = inputData.split(" ");
            String method = arr[0];
            String route = arr[1];
            return new Request(route, method);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RequestHandler findRequestHandler(Request request) {
        for (RequestHandler requestHandler : requestHandlers) {
            if (requestHandler.getMethod().equals(request.getMethod()) && requestHandler.getRoute().equals(request.getRoute())) {
                return requestHandler;
            }
        }
        return errorRequestHandler;
    }
}
