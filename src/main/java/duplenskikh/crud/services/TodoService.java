package duplenskikh.crud.services;

import duplenskikh.crud.entities.Todo;
import duplenskikh.crud.repositories.TodoRepository;

import java.util.ArrayList;

public class TodoService implements Service<Todo> {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Todo todo) {
        repository.create(todo);
    }

    @Override
    public ArrayList<Todo> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean update(Todo todo, String query) {
        int updateCount = repository.update(todo, query);
        return updateCount != 0; // count=0 => false (ничего не изменилось)
    }

    @Override
    public boolean delete(String title) {
        int updateCount = repository.delete(title);
        return updateCount != 0;
    }
}
