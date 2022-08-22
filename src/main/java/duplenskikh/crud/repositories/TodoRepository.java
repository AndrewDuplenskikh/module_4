package duplenskikh.crud.repositories;

import duplenskikh.crud.DBExecutor;
import duplenskikh.crud.entities.Todo;

import java.sql.SQLException;
import java.util.ArrayList;

public class TodoRepository implements Repository<Todo> {
    private final DBExecutor executor;
    private final String DB_NAME = "todos";

    public TodoRepository(DBExecutor executor) {
        this.executor = executor;
        createTable();
    }

    @Override
    public void createTable() {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("create table if not exists ")
                    .append(DB_NAME)
                    .append(" (id bigint auto_increment, title varchar(256), text varchar(256), primary key (id))");
            executor.execUpdate(request.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void create(Todo instance) {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("insert into ")
                    .append(DB_NAME)
                    .append(" (title, text) values('")
                    .append(instance.getTitle())
                    .append("','")
                    .append(instance.getText())
                    .append("')");
            executor.execUpdate(request.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Todo get(String title) {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("select title, text from ")
                    .append(DB_NAME);
            Todo todo = executor.execQuery(request.toString(), resultSet -> {
                if (resultSet.next()) {
                    return new Todo(resultSet.getString("title"), resultSet.getString("text"));
                }
                return new Todo(null, null);
            });
            return todo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Todo> getAll() {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("select * from ")
                    .append(DB_NAME);
            ArrayList<Todo> todos = executor.execQuery(request.toString(), resultSet -> {
                ArrayList<Todo> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(new Todo(resultSet.getString("title"), resultSet.getString("text")));
                }
                return result;
            });
            return todos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Todo todo, String title) {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("update ")
                    .append(DB_NAME)
                    .append(" set title='")
                    .append(todo.getTitle())
                    .append("', text='")
                    .append(todo.getText())
                    .append("' where title='")
                    .append(title)
                    .append("'");
            int updateCount = executor.execUpdate(request.toString());
            return updateCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(String title) {
        try {
            StringBuilder request = new StringBuilder();
            request
                    .append("delete from ")
                    .append(DB_NAME)
                    .append(" where title='")
                    .append(title)
                    .append("'");
            int updateCount = executor.execUpdate(request.toString());
            return updateCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
