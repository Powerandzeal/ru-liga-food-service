INSERT INTO courier (name,phoneNumber,statusOrder,coordinate) values ('ivan',232323,'get', '1232.323');
INSERT INTO customers (phone,email,address) values ('79290473013','seregaP@gmail.com','Нижний Новгород,ул Веденяпина,д2,кв 32');
INSERT INTO restaurants (name_restaurant,address,status_restaurant) values ('Kfc','Проспект молодежынй,д32', 'Created');
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Картошка фри',120, 'Обжаренный картофель','image123');
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Чикен бургер',170, 'Бургер из куриной котлеты, с огурцами и помидорами и мягкой булочки','image133');
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Добрый кола',90, 'Лимонад','image134');
INSERT INTO orders (id_customer,id_restaurants,statusOrder,id_courier,ordertime) values (1,1,'Cooking', 1,'2023-10-19 14:30:00');
INSERT INTO order_items (id_order,id_restaurant_menu_items,price,quantity) values (1,1,1200,1);
