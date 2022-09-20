package com.tensql.requests;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/new_product";
    private static final String USERNAME = "brd";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = connection.createStatement();

            Scanner reader = new Scanner(System.in);
            while (true) {
                String input = reader.nextLine();
                if (input.equalsIgnoreCase("help")) {
                    ProductQuery.help();
                    continue;
                } else if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                try {
                    if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 11) {
                        switch (Integer.parseInt(input)) {
                            case 1:
                                ResultSet one = stmt.executeQuery(Requests.number(1));
                                System.out.printf("%-20s%-8s%n", "customer_name", "quantity");
                                while (one.next()) {
                                    System.out.printf("%-20s%-8s%n", one.getString(1), one.getInt(2));
                                }
                                break;
                            case 2:
                                ResultSet two = stmt.executeQuery(Requests.number(2));
                                System.out.printf("%-15s%-8s%-10s%n", "product_name", "count", "customer_name");
                                while (two.next()) {
                                    System.out.printf("%-15s%-8s%-10s%n", two.getString(1), two.getInt(2), two.getString(3));
                                }
                                break;
                            case 3:
                                ResultSet three = stmt.executeQuery(Requests.number(3));
                                System.out.printf("%-15s%-8s%-10s%n", "product_name", "price", "sale");
                                while (three.next()) {
                                    System.out.printf("%-15s%-8s%-10s%n", three.getString(1), three.getInt(2), three.getInt(3));
                                }
                                break;
                            case 4:
                                ResultSet four = stmt.executeQuery(Requests.number(4));
                                System.out.printf("%-20s%-10s%n", "customer_name", "email");
                                while (four.next()) {
                                    System.out.printf("%-20s%-10s%n", four.getString(1), four.getString(2));
                                }
                                break;
                            case 5:
                                ResultSet five = stmt.executeQuery(Requests.number(5));
                                System.out.printf("%-15s%-14s%-10s%n", "customer_name", "", "date");
                                while (five.next()) {
                                    System.out.printf("%-15s%-14s%-10s%n", five.getString(1), five.getString(2), five.getDate(3));
                                }
                                break;
                            case 6:
                                ResultSet six = stmt.executeQuery(Requests.number(6));
                                System.out.printf("%-20s%-30s%-10s%n", "customer_name", "email", "product_name");
                                while (six.next()) {
                                    System.out.printf("%-20s%-30s%-10s%n", six.getString(1), six.getString(2), six.getString(3));
                                }
                                break;
                            case 7:
                                ResultSet seven = stmt.executeQuery(Requests.number(7));
                                System.out.println("Median");
                                while (seven.next()) {
                                    System.out.println(seven.getInt(1));
                                }
                                break;
                            case 8:
                                ResultSet eight = stmt.executeQuery(Requests.number(8));
                                System.out.println("count");
                                while (eight.next()) {
                                    System.out.println(eight.getInt(1));
                                }
                                break;
                            case 9:
                                ResultSet nine = stmt.executeQuery(Requests.number(9));
                                System.out.printf("%-20s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n", "product_name", "monday",
                                        "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
                                while (nine.next()) {
                                    System.out.printf("%-20s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n", nine.getString(1), nine.getInt(2),
                                            nine.getInt(3), nine.getInt(4), nine.getInt(5), nine.getInt(6), nine.getInt(7), nine.getInt(8));
                                }
                                break;
                            case 10:
                                ResultSet ten = stmt.executeQuery(Requests.number(10));
                                System.out.printf("%-20s%-10s%n", "product_name", "income");
                                while (ten.next()) {
                                    System.out.printf("%-20s%-10s%n", ten.getString(1), ten.getInt(2));
                                }
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println("Wrong command, use on of these commands.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Wrong command, use on of these commands.");
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
    }
}
