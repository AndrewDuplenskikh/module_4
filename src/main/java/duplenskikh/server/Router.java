package duplenskikh.server;

import duplenskikh.server.requestHandlers.ErrorRequestHandler;
import duplenskikh.server.requestHandlers.MyRequestHandler;
import duplenskikh.server.requestHandlers.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Router {
    private final RequestHandler[] requestHandlers = new RequestHandler[]{new MyRequestHandler()};
    private final ArrayList<RequestHandler> arrayList = new ArrayList<>();
    private BufferedReader input;

    public Router(InputStream inputStream) {
        input = new BufferedReader(new InputStreamReader(inputStream));
        arrayList.add(new MyRequestHandler());
    }

    public void listenInputStream() {
        try {
            String inputData = input.readLine();
            String[] arr = inputData.split(" ");
            String method = arr[0];
            String route = arr[1];
            System.out.println(method);
            System.out.println(route);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private RequestHandler findRequestHandlerRoute(String route) {

        for (RequestHandler requestHandler : requestHandlers) {
            if (requestHandler.getRoute().equals(route)) {
                return requestHandler;
            }
        }
        return new ErrorRequestHandler();
    }
}
