package duplenskikh.crud.controllers;

import duplenskikh.crud.services.TodoService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteTodoController extends HttpServlet {
    private final TodoService todoService;

    public DeleteTodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String title = request.getParameter("title");
            PrintWriter printWriter = response.getWriter();
            if (!todoService.delete(title)) {
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
