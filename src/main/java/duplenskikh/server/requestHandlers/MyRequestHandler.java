package duplenskikh.server.requestHandlers;

import duplenskikh.server.HtmlReader;
import duplenskikh.server.Request;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

public class MyRequestHandler implements RequestHandler {
    private final Request request;
    private final String filename;

    public MyRequestHandler(Request request, String filename) {
        this.request = request;
        this.filename = filename;
    }

    @Override
    public String getRoute() {
        return request.getRoute();
    }

    @Override
    public String getMethod() {
        return request.getMethod();
    }

    @Override
    public void handleRequest(OutputStream outputStream, Reader reader) {
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
