package duplenskikh.jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        String query = "select name from students where id=1";
        String name = Executor.execQuery(connection, query, resultSet ->
        {
            resultSet.next();
            return resultSet.getString("name");
        });
        System.out.println(name);

    }
}
