package com.tensql.requests;

public class Requests {
    int request;

    public static String query(String command) {
        switch (command) {
            case "1":
                return "select c.name , sum(oi.quantity) quantity from customers c " +
                        "join orders o ON o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where p.name = 'icar'" +
                        "group by c.name";
            case "2":
                return "select p.name, sum(oi.quantity) count, c.name  from customers c " +
                        "join orders o ON o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where o.orders_date between '2022-06-06 00:00:00' and '2022-06-06 23:59:59' " +
                        "group by c.name, p.name " +
                        "order by count desc";
            case "3":
                return "select p.name, p.price, sum(oi.quantity) sale from product p " +
                        "join order_item oi on oi.product_id = p.id " +
                        "group by p.name, p.price " +
                        "order by sale desc " +
                        "limit 5";
            case "4":
                return "select distinct c.name, c.email from customers c " +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where p.name like 'iphone%' " +
                        "order by c.name desc ";
            case "5":
                return "select c.name name_people, p.name name_product, o.orders_date date from customers c \n" +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id  = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "group by c.name " +
                        "having  sum(oi.quantity) = 2";
            case "6":
                return "select c.name name_people, c.email, p.name from customers c " +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "group by c.name " +
                        "having sum(p.price * oi.quantity) > 5000";
            case "7":
                return "select avg(summ) from (select o.id, sum(p.price * oi.quantity) summ, " +
                        "row_number() over (order by sum(p.price * oi.quantity) desc) as r, " +
                        "    count(o.id) over () as c " +
                        "    from product p, order_item oi, orders o " +
                        "where p.id = oi.product_id " +
                        "and oi.order_id = o.id " +
                        "group by o.id " +
                        "order by summ desc) luboi " +
                        "where r in (floor((c+1)/2), ceil((c+1)/2))";
            case "8":
                return "select count(c.id) count from customers c, orders o " +
                        "where c.id = o.customer_id and o.id > 0";
            case "9":
                return "select pp.name, (select count(oi.quantity)  \n" +
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
                        "from product pp";
            case "10":
                return "select p.name, sum(p.price * oi.quantity) income from product p, order_item oi, orders o \n" +
                        "where \tp.id = oi.product_id " +
                        "\t\tand oi.order_id = o.id " +
                        "\t\tand year(o.orders_date) = 2022 " +
                        "group by p.name " +
                        "order by income desc";
        }
        return null;
    }
}
