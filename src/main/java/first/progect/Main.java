package first.progect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/new_product";
    private static final String USERNAME = "brd";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection;
        PrintWriter pw = responce.ge

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = connection.createStatement();
            Scanner reader = new Scanner(System.in);
            int input = reader.nextInt();
            switch (input) {
                case 1: ResultSet one = stmt.executeQuery("select c.name , sum(oi.quantity) quantity from customers c \n" +
                        "\tjoin orders o ON o.customer_id = c.id \n" +
                        "\tjoin order_item oi on oi.order_id = o.id \n" +
                        "\tjoin product p on p.id = oi.product_id \n" +
                        "where p.name = 'icar'\n" +
                        "group by c.name");
                break;
                case 2: ResultSet two = stmt.executeQuery("select p.name, sum(oi.quantity) count, c.name  from customers c \n" +
                        "\tjoin orders o ON o.customer_id = c.id \n" +
                        "\tjoin order_item oi on oi.order_id = o.id \n" +
                        "\tjoin product p on p.id = oi.product_id \n" +
                        "where o.orders_date between '2022-06-06 00:00:00' and '2022-06-06 23:59:59'\n" +
                        "group by c.name, p.name\n" +
                        "order by count desc");
                break;
                default:
                    break;
            }




            connection.close();
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
    }
}
