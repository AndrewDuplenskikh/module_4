package duplenskikh.jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
//        String request = "create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))";
        String query = "select name from students where id=1";
        String name = Executor.execQuery(connection, query, resultSet ->
        {
            resultSet.next();
            return resultSet.getString("name");
        });
        System.out.println(name);
        String update = "insert into students values(6, \"pussy\", 22, \"woman\",\"1994-10-31\");";
        Executor.execUpdate(connection, update);
    }
}
