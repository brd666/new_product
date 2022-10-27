package com.tensql.requests;

import java.util.ArrayList;
import java.util.List;

public class Initializer {

//    public static void main(String[] args) {
//        Initializer initializer = new Initializer();
//        initializer.initializer();
//        System.out.println(sqlList.get(9).getNumber());
//        System.out.println(sqlList.size());
//    }

    public static List<StupidStoreRequest> initialize() {
        List<StupidStoreRequest> sqlList = new ArrayList<>();

        sqlList.add(new StupidStoreRequest(
                "select c.name , sum(oi.quantity) quantity from customers c " +
                        "join orders o ON o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where p.name = 'icar'" +
                        "group by c.name", "Имена покупателей, кто купил icar и сколько.", 1));
        sqlList.add(new StupidStoreRequest(
                "select p.name, sum(oi.quantity) count, c.name  from customers c " +
                        "join orders o ON o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where o.orders_date between '2022-06-06 00:00:00' and '2022-06-06 23:59:59' " +
                        "group by c.name, p.name " +
                        "order by count desc", "Какие товары, в каком количестве и кто купили 6 июня 2022 года? Отсортировать по количеству.", 2));
        sqlList.add(new StupidStoreRequest(
                "select p.name, p.price, sum(oi.quantity) sale from product p " +
                        "join order_item oi on oi.product_id = p.id " +
                        "group by p.name, p.price " +
                        "order by sale desc " +
                        "limit 5", "Вывести имя, цену и количество 5-ти самых продаваемых товаров.", 3));
        sqlList.add(new StupidStoreRequest(
                "select distinct c.name, c.email from customers c " +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "where p.name like 'iphone%' " +
                        "order by c.name desc ", "Вывести имена и почту людей, кто покупал любой iphone. Отсортировать от я до а.", 4));
        sqlList.add(new StupidStoreRequest(
                "select c.name name_people, p.name name_product, o.orders_date date from customers c \n" +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id  = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "group by c.name " +
                        "having  sum(oi.quantity) = 2", "Вывести имена людей, их покупки и дату, которые купили по два продукта. Отфильтровать по дате.", 5));
        sqlList.add(new StupidStoreRequest(
                "select c.name name_people, c.email, p.name from customers c " +
                        "join orders o on o.customer_id = c.id " +
                        "join order_item oi on oi.order_id = o.id " +
                        "join product p on p.id = oi.product_id " +
                        "group by c.name " +
                        "having sum(p.price * oi.quantity) > 5000", "Вывести имена, почту людей и продукт, которые закупились больше чем на 5000.", 6));
        sqlList.add(new StupidStoreRequest(
                "select avg(summ) from (select o.id, sum(p.price * oi.quantity) summ, " +
                        "row_number() over (order by sum(p.price * oi.quantity) desc) as r, " +
                        "    count(o.id) over () as c " +
                        "    from product p, order_item oi, orders o " +
                        "where p.id = oi.product_id " +
                        "and oi.order_id = o.id " +
                        "group by o.id " +
                        "order by summ desc) luboi " +
                        "where r in (floor((c+1)/2), ceil((c+1)/2))", "Вывести среднюю стоимость корзины (медианную стоимость корзины).", 7));
        sqlList.add(new StupidStoreRequest("select count(c.id) count from customers c, orders o " +
                "where c.id = o.customer_id and o.id > 0", "Количество пользователей у которых есть хотя-бы одна покупка (без джоинов).", 8));
        sqlList.add(new StupidStoreRequest(
                "select pp.name, (select count(oi.quantity)  \n" +
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
                        "from product pp", "Вывести таблицу где будет 8 колонок: первая это название продукта, остальные семь сколько их было куплено в каждый день недели.", 9));
        sqlList.add(new StupidStoreRequest(
                "select p.name, sum(p.price * oi.quantity) income from product p, order_item oi, orders o \n" +
                        "where \tp.id = oi.product_id " +
                        "\t\tand oi.order_id = o.id " +
                        "\t\tand year(o.orders_date) = 2022 " +
                        "group by p.name " +
                        "order by income desc", "Вывести продукты в порядке по убыванию принесённой прибыли за последний год.", 10));
        return sqlList;
    }
}
