package duplenskikh.crud.repositories;

import duplenskikh.crud.entities.Persistable;

import java.util.ArrayList;

public interface Repository<T extends Persistable> {
    void createTable();

    void create(T instance);

    T get(String query);

    ArrayList<T> getAll();

    int update(T instance, String query);

    int delete(String query);
}
