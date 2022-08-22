package duplenskikh.simplejetty;

import duplenskikh.server.HtmlReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            response.setContentType("text/html");
            response.getWriter().printf("Hello %s, my dear friend!", name);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
