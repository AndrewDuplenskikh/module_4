package duplenskikh.server.requestHandlers;

import duplenskikh.server.HtmlReader;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MyRequestHandler implements RequestHandler {
    private final String route;
    private final String filename;

    public MyRequestHandler(String route, String filename) {
        this.route = route;
        this.filename = filename;
    }

    @Override
    public String getRoute() {
        return route;
    }

    @Override
    public void handleRequest(OutputStream outputStream) {
        String response = HtmlReader.readHtml(filename);
        PrintWriter output = new PrintWriter(outputStream);
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println(response);
        output.flush();
        output.close();
    }
}
