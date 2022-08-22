package duplenskikh.crud.controllers;

import duplenskikh.crud.entities.Todo;
import duplenskikh.crud.services.TodoService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTodoController extends HttpServlet {
    private final TodoService todoService;

    public CreateTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String title = request.getParameter("title");
            String text = request.getParameter("text");
            todoService.create(new Todo(title, text));
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.sendRedirect("http://localhost:8080/index");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
