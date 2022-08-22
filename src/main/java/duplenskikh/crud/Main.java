package duplenskikh.crud;

import duplenskikh.crud.controllers.CreateTodoController;
import duplenskikh.crud.controllers.DeleteTodoController;
import duplenskikh.crud.controllers.TodoListController;
import duplenskikh.crud.controllers.UpdateTodoController;
import duplenskikh.crud.repositories.TodoRepository;
import duplenskikh.crud.services.TodoService;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        DBExecutor executor = new DBExecutor(dbConnection.getConnection());

        CustomServer server = new CustomServer(8080);

        TodoService todoService = new TodoService(new TodoRepository(executor));
        CreateTodoController createTodoController = new CreateTodoController(todoService);
        DeleteTodoController deleteTodoController = new DeleteTodoController(todoService);
        TodoListController todoListController = new TodoListController(todoService);
        UpdateTodoController updateTodoController = new UpdateTodoController(todoService);

        server.addController(todoListController, "/index");
        server.addController(createTodoController, "/create");
        server.addController(deleteTodoController, "/delete");
        server.addController(updateTodoController, "/update");
        server.setHandler();

        server.start();
    }
}
