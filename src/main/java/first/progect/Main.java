package first.progect;

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
                if (input.equals("help")) {
                    System.out.println("1. Имена покупателей, кто купил icar и сколько. \n" +
                            "2. Какие товары, в каком количестве и кто купили 6 июня 2022 года? Отсортировать по количеству. \n" +
                            "3. Вывести имя, цену и количество 5-ти самых продаваемых товаров. \n" +
                            "4. Вывести имена и почту людей, кто покупал любой iphone. Отсортировать от я до а. \n" +
                            "5. Вывести имена людей, их покупки и дату, которые купили по два продукта. Отфильтровать по дате. \n" +
                            "6. Вывести имена, почту людей и продукт, которые закупились больше чем на 5000 \n" +
                            "7. Вывести среднюю стоимость корзины (медианную стоимость корзины) \n" +
                            "8. Количество пользователей у которых есть хотябы одна покупка (без джоинов) \n" +
                            "9. Вывести таблицу где будет 8 колонок: первая это название продукта, остальные семь сколько их было " +
                            "куплено в каждый день недели. \n" +
                            "10. Вывести продукты в порядке по убыванию принесённой прибыли за последний год.  ");
                } else if (input.equals("exit")) {
                    break;
                } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < 11) {
                    switch (Integer.parseInt(input)) {
                        case 1:
                            ResultSet one = stmt.executeQuery("select c.name , sum(oi.quantity) quantity from customers c " +
                                    "join orders o ON o.customer_id = c.id " +
                                    "join order_item oi on oi.order_id = o.id " +
                                    "join product p on p.id = oi.product_id " +
                                    "where p.name = 'icar'" +
                                    "group by c.name");
                            System.out.printf("%-20s%-8s%n", "customer_name", "quantity");
                            while (one.next()) {
                                System.out.printf("%-20s%-8s%n", one.getString(1), one.getInt(2));
                            }
                            break;
                        case 2:
                            ResultSet two = stmt.executeQuery("select p.name, sum(oi.quantity) count, c.name  from customers c " +
                                    "join orders o ON o.customer_id = c.id " +
                                    "join order_item oi on oi.order_id = o.id " +
                                    "join product p on p.id = oi.product_id " +
                                    "where o.orders_date between '2022-06-06 00:00:00' and '2022-06-06 23:59:59' " +
                                    "group by c.name, p.name " +
                                    "order by count desc");
                            System.out.printf("%-15s%-8s%-10s%n", "product_name", "count", "customer_name");
                            while (two.next()) {
                                System.out.printf("%-15s%-8s%-10s%n", two.getString(1), two.getInt(2), two.getString(3));
                            }
                            break;
                        case 3:
                            ResultSet three = stmt.executeQuery("select p.name, p.price, sum(oi.quantity) sale from product p " +
                                    "join order_item oi on oi.product_id = p.id " +
                                    "group by p.name, p.price " +
                                    "order by sale desc " +
                                    "limit 5");
                            System.out.printf("%-15s%-8s%-10s%n", "product_name", "price", "sale");
                            while (three.next()) {
                                System.out.printf("%-15s%-8s%-10s%n", three.getString(1), three.getInt(2), three.getInt(3));
                            }
                            break;
                        case 4:
                            ResultSet four = stmt.executeQuery("select distinct c.name, c.email from customers c " +
                                    "join orders o on o.customer_id = c.id " +
                                    "join order_item oi on oi.order_id = o.id " +
                                    "join product p on p.id = oi.product_id " +
                                    "where p.name like 'iphone%' " +
                                    "order by c.name desc ");
                            System.out.printf("%-20s%-10s%n", "customer_name", "email");
                            while (four.next()) {
                                System.out.printf("%-20s%-10s%n", four.getString(1), four.getString(2));
                            }
                            break;
                        case 5:
                            ResultSet five = stmt.executeQuery("select c.name name_people, p.name name_product, o.orders_date date from customers c \n" +
                                    "join orders o on o.customer_id = c.id " +
                                    "join order_item oi on oi.order_id  = o.id " +
                                    "join product p on p.id = oi.product_id " +
                                    "group by c.name " +
                                    "having  sum(oi.quantity) = 2");
                            System.out.printf("%-15s%-14s%-10s%n", "customer_name", "", "date");
                            while (five.next()) {
                                System.out.printf("%-15s%-14s%-10s%n", five.getString(1), five.getString(2), five.getDate(3));
                            }
                            break;
                        case 6:
                            ResultSet six = stmt.executeQuery("select c.name name_people, c.email, p.name from customers c " +
                                    "join orders o on o.customer_id = c.id " +
                                    "join order_item oi on oi.order_id = o.id " +
                                    "join product p on p.id = oi.product_id " +
                                    "group by c.name " +
                                    "having sum(p.price * oi.quantity) > 5000");
                            System.out.printf("%-20s%-30s%-10s%n", "customer_name", "email", "product_name");
                            while (six.next()) {
                                System.out.printf("%-20s%-30s%-10s%n", six.getString(1), six.getString(2), six.getString(3));
                            }
                            break;
                        case 7:
                            ResultSet seven = stmt.executeQuery("select avg(summ) from (select o.id, sum(p.price * oi.quantity) summ, " +
                                    "row_number() over (order by sum(p.price * oi.quantity) desc) as r, " +
                                    "    count(o.id) over () as c " +
                                    "    from product p, order_item oi, orders o " +
                                    "where p.id = oi.product_id " +
                                    "and oi.order_id = o.id " +
                                    "group by o.id " +
                                    "order by summ desc) luboi " +
                                    "where r in (floor((c+1)/2), ceil((c+1)/2))");
                            System.out.println("Median");
                            while (seven.next()) {
                                System.out.println(seven.getInt(1));
                            }
                            break;
                        case 8:
                            ResultSet eight = stmt.executeQuery("select count(c.id) count from customers c, orders o " +
                                    "where c.id = o.customer_id and o.id > 0");
                            System.out.println("count");
                            while (eight.next()) {
                                System.out.println(eight.getInt(1));
                            }
                            break;
                        case 9:
                            ResultSet nine = stmt.executeQuery("select pp.name, (select count(oi.quantity)  \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 2\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) monday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 3\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) tuesday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 4\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) wednesday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 5\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) thursday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 6\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) friday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 7\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) saturday, \n" +
                                    "(select count(oi.quantity) \n" +
                                    "from product p, order_item oi, orders o \n" +
                                    "where p.id = oi.product_id \n" +
                                    "and oi.order_id = o.customer_id \n" +
                                    "and dayofweek(o.orders_date) = 1\n" +
                                    "and p.id = pp.id\n" +
                                    "group by p.id) sunday\n" +
                                    "from product pp");
                            System.out.printf("%-20s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n", "product_name", "monday",
                                    "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
                            while (nine.next()) {
                                System.out.printf("%-20s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n", nine.getString(1), nine.getInt(2),
                                        nine.getInt(3), nine.getInt(4), nine.getInt(5), nine.getInt(6), nine.getInt(7), nine.getInt(8));
                            }
                            break;
                        case 10:
                            ResultSet ten = stmt.executeQuery("select p.name, sum(p.price * oi.quantity) income from product p, order_item oi, orders o \n" +
                                    "where \tp.id = oi.product_id " +
                                    "\t\tand oi.order_id = o.id " +
                                    "\t\tand year(o.orders_date) = 2022 " +
                                    "group by p.name " +
                                    "order by income desc");
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

            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
    }
}
