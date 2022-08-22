package duplenskikh.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBExecutor {
    private Connection connection;

    public DBExecutor(Connection connection) {
        this.connection = connection;
    }

    public int execUpdate(String update) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(update);
        int updateCount = statement.getUpdateCount();
        statement.close();
        return updateCount;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);
        resultSet.close();
        statement.close();
        return value;
    }
}
