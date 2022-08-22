package duplenskikh.crud.controllers;

import duplenskikh.crud.entities.Todo;
import duplenskikh.crud.services.TodoService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateTodoController  extends HttpServlet {
    private final TodoService todoService;

    public UpdateTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String title = request.getParameter("title");
            String newTitle = request.getParameter("newtitle");
            String newText = request.getParameter("newtext");
            PrintWriter printWriter = response.getWriter();
            if (!todoService.update(new Todo(newTitle, newText), title)) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                printWriter.println("todo not found");
                return;
            }
            response.setStatus(HttpServletResponse.SC_OK);
            printWriter.println("Success");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
