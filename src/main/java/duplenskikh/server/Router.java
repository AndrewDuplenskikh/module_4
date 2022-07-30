package duplenskikh.server;

import duplenskikh.server.requestHandlers.ErrorRequestHandler;
import duplenskikh.server.requestHandlers.MyRequestHandler;
import duplenskikh.server.requestHandlers.RequestHandler;

import java.io.*;
import java.util.ArrayList;

public class Router {
    // TODO arrayList => map
    private final ArrayList<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();
    private final BufferedReader input;
    private final OutputStream outputStream;
    private final RequestHandler errorRequestHandler = new ErrorRequestHandler("/error", "error.html");

    public Router(InputStream inputStream, OutputStream outputStream) {
        input = new BufferedReader(new InputStreamReader(inputStream));
        this.outputStream = outputStream;
        requestHandlers.add(new MyRequestHandler("/", "index.html"));
        requestHandlers.add(new MyRequestHandler("/another", "another.html"));
    }

    public void listen() {
        String route = getRequestRoute();
        RequestHandler requestHandler = findRequestHandlerRoute(route);
        requestHandler.handleRequest(outputStream);
    }

    private String getRequestRoute() {
        try {
            String inputData = input.readLine();
            String[] arr = inputData.split(" ");
            String method = arr[0];
            String route = arr[1];
            return route;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RequestHandler findRequestHandlerRoute(String route) {
        for (RequestHandler requestHandler : requestHandlers) {
            if (requestHandler.getRoute().equals(route)) {
                return requestHandler;
            }
        }
        return errorRequestHandler;
    }
}
