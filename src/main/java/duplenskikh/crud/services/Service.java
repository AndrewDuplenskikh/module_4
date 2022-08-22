package duplenskikh.crud.services;

import duplenskikh.crud.entities.Persistable;

import java.util.ArrayList;

public interface Service<T extends Persistable > {
    void create(T instance);

    ArrayList<T> getAll();

    boolean update(T instance, String query);

    boolean delete(String query);
}
