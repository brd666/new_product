select pp.name, (select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 2
						and p.id = pp.id
						group by p.id) monday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 3
						and p.id = pp.id
						group by p.id) tuesday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 4
						and p.id = pp.id
						group by p.id) wednesday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 5
						and p.id = pp.id
						group by p.id) thursday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 6
						and p.id = pp.id
						group by p.id) friday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 7
						and p.id = pp.id
						group by p.id) saturday,
				(select count(oi.quantity)
					from product p, order_item oi, orders o
						where p.id = oi.product_id
						and oi.order_id = o.customer_id
						and dayofweek(o.orders_date) = 1
						and p.id = pp.id
						group by p.id) sunday
from product pp