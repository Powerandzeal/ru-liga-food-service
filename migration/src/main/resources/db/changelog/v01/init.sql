-- Создание таблицы "Customers"
CREATE TABLE Customers
(
    customer_id SERIAL PRIMARY KEY,
    phone       VARCHAR(15),
    email       VARCHAR(255),
    address     VARCHAR(255)

);

CREATE TABLE Couriers
(
    courier_id  SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    phoneNumber VARCHAR(15),
    statusOrder VARCHAR(255),
    coordinate  VARCHAR(255)
);
-- Создание таблицы "Рестораны"
CREATE TABLE Restaurants
(
    restaurant_id     SERIAL PRIMARY KEY,
    name_restaurant   VARCHAR(255),
    address           VARCHAR(255),
    Status_restaurant VARCHAR(255)

);
-- Создание таблицы "Заказы"
CREATE TABLE Orders
(
    order_id       SERIAL PRIMARY KEY,
    id_Customer    INT REFERENCES Customers (customer_id),
    id_Restaurants INT REFERENCES Restaurants (restaurant_id),
    statusOrder    VARCHAR(255),
    id_Courier     INT REFERENCES Couriers (courier_id),
    OrderTime      TIMESTAMP,
    price          float
);
CREATE TABLE Restaurant_Menu_Items
(
    restaurant_menu_item_id SERIAL PRIMARY KEY,
    id_restaurant           INT REFERENCES Restaurants (restaurant_id),
    name_items              VARCHAR(255),
    price                   float,
    description             VARCHAR(255),
    image                   bytea
);
-- Создание таблицы "Заказ_айтемы"
CREATE TABLE Order_Items
(
    order_items_id           SERIAL PRIMARY KEY,
    id_Order                 INT REFERENCES Orders (order_id),
    id_Restaurant_Menu_Items INT REFERENCES Restaurant_Menu_Items (restaurant_menu_item_id),
    price                    float,
    quantity                 INT
);

-- Создание таблицы "Меню_айтемы"

