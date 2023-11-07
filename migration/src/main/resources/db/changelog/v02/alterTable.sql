ALTER TABLE Orders ADD COLUMN create_time TIMESTAMP;
    INSERT INTO orders (id_customer,id_restaurants,statusOrder,id_courier,ordertime,create_time) values (1,1,'DELIVERY_PENDING', 1,'2023-10-19 14:30:00','2023-10-19 14:30:00');
INSERT INTO order_items (id_order,id_restaurant_menu_items,price,quantity) values (1,1,1200,1);