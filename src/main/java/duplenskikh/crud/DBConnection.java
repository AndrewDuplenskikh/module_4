package duplenskikh.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        try {
            this.connection = DriverManager.getConnection(getDatabaseUrl());
            if (connection.isValid(10)) {
                System.out.println("Соединение установлено! Я в успехе, успех во мне!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private String getDatabaseUrl() {
        StringBuilder url = new StringBuilder();
        url.append(DBSettings.DB_PREFIX)
                .append(DBSettings.DB_HOST)
                .append(DBSettings.DB_PORT)
                .append(DBSettings.DB_NAME)
                .append(DBSettings.DB_USER)
                .append(DBSettings.DB_PASSWORD);
        return url.toString();
    }
}
