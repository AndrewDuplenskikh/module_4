package duplenskikh.simplejetty;

import duplenskikh.server.HtmlReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter printWriter = response.getWriter();
            String htmlDocument = HtmlReader.readHtml("src\\main\\java\\duplenskikh\\simplejetty\\resourses\\index.html");
            printWriter.println(htmlDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
