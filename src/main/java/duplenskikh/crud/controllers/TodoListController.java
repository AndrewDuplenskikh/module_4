package duplenskikh.crud.controllers;

import duplenskikh.crud.entities.Todo;
import duplenskikh.crud.services.TodoService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TodoListController extends HttpServlet {
    private final TodoService todoService;

    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Todos page</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<main>");
            ArrayList<Todo> todos = todoService.getAll();
            todos.forEach(todo -> {
                printWriter.println(todo.toString());
            });
            printWriter.println(
                    "<form action=\"/create\"  method=\"post\">\n" +
                            "        <h2>Create TODO:</h2>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"title\" placeholder=\"title\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"text\" placeholder=\"text\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "           <button>add new todo</button>\n" +
                            "        </div>\n" +
                            "    </form>\n" +
                            "<form action=\"/delete\"  method=\"post\">\n" +
                            "        <h2>Delete TODO:</h2>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"title\" placeholder=\"title\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "           <button>delete todo</button>\n" +
                            "        </div>\n" +
                            "    </form>\n" +
                            "<form action=\"/update\"  method=\"post\">\n" +
                            "        <h2>Update TODO:</h2>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"title\" placeholder=\"title\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"newtitle\" placeholder=\"new title\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "            <label>\n" +
                            "                <input type=\"text\" name=\"newtext\" placeholder=\"new text\">\n" +
                            "            </label>\n" +
                            "        </div>\n" +
                            "        <div>\n" +
                            "           <button>update todo</button>\n" +
                            "        </div>\n" +
                            "    </form>\n" +
                            "</main>\n" +
                            "</body>\n" +
                            "</html>");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
