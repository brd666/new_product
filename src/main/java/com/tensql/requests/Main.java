package com.tensql.requests;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/new_product";
    private static final String USERNAME = "brd";
    private static final String PASSWORD = "root";

    private static HashMap<String, CommandVariant> initCv(Statement stmt) {
        HashMap<String, CommandVariant> result = new HashMap<>();

        //help
        HelpVariant helpVariant = new HelpVariant();
        result.put(helpVariant.name, helpVariant);

        //exit
        ExitVariant exitVariant = new ExitVariant();
        result.put(exitVariant.name, exitVariant);

        // 1-10
        for (int i = 1; i < 11; i++) {
            NumberVariant cv = new NumberVariant(String.valueOf(i), stmt);
            result.put(cv.name, cv);
        }
        return result;
    }

    public static void main(String[] args) {
        Connection connection;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = connection.createStatement();
            HashMap<String, CommandVariant> cv = initCv(stmt);

            Scanner reader = new Scanner(System.in);
            try {
                while (true) {
                    String input = reader.nextLine();
                    CommandVariant inputcv = cv.get(input);
                    if (inputcv == null) {
                        System.out.println("Wrong command, use on of these commands.");
                    } else {
                        inputcv.execute();
                    }
                }
            } catch (Exception e) {

            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
    }
}
