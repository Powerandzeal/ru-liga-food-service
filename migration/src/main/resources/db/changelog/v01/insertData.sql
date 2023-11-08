INSERT INTO users (username,password_hash,role) values ('79290473013','fsafsdfsd', 'COURIER');
INSERT INTO users (username,password_hash,role) values ('kfc','fsafsdfsd', 'COURIER');
INSERT INTO users (username,password_hash,role) values ('222222222222','fsafsdfsd', 'COURIER');

INSERT INTO couriers (name,phoneNumber,statusOrder,coordinate,courier_user_id) values ('ivan',232323,'DELIVERY_PENDING', '1232.323',1);
INSERT INTO customers (phone,email,address,customer_user_id) values ('79290473013','seregaP@gmail.com','Нижний Новгород,ул Веденяпина,д2,кв 32',2);
INSERT INTO restaurants (name_restaurant,address,status_restaurant,restaurant_user_id) values ('Kfc','Проспект молодежынй,д32', 'Created',3);
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Картошка фри',120.0, 'Обжаренный картофель','image123');
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Чикен бургер',170.0, 'Бургер из куриной котлеты, с огурцами и помидорами и мягкой булочки','image133');
INSERT INTO restaurant_menu_items (id_restaurant,name_items,price,description,image)
values (1,'Добрый кола',90.0, 'Лимонад','image134');

