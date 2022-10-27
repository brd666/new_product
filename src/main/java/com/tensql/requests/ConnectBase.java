package com.tensql.requests;

import java.sql.*;

public class ConnectBase {
    private static final String URL = "jdbc:mysql://localhost:3306/new_product";
    private static final String USERNAME = "brd";
    private static final String PASSWORD = "root";
    Statement stmt;
    Connection connection;

    public void toConnect() {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
    }
}
