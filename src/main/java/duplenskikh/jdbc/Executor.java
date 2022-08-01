package duplenskikh.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    public static int execUpdate(Connection connection, String update) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(update);
            int updatedLines = statement.getUpdateCount();
            statement.close();
            return updatedLines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

//    public static ResultSet execQuery(Connection connection, String query, )

    public static <T> T execQuery(Connection connection, String query, TResultHandler<T> handler) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            T value = handler.handle(resultSet);
            resultSet.close();
            statement.close();
            return value;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
