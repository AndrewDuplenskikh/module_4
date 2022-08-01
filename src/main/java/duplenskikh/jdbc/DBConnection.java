package duplenskikh.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        StringBuilder url = new StringBuilder();
        url.append(Settings.DB_PREFIX)
                .append(Settings.DB_HOST)
                .append(Settings.DB_PORT)
                .append(Settings.DB_NAME)
                .append(Settings.DB_USER)
                .append(Settings.DB_PASSWORD);
        try {
            Connection connection = DriverManager.getConnection(url.toString());
            if (connection.isValid(10)) {
                System.out.println("Соединение установлено! Я в успехе, успех во мне!");
            }
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
