-- Создание таблицы "Customers"
CREATE TABLE Customers
(
    id      SERIAL PRIMARY KEY,
    phone   VARCHAR(15),
    email   VARCHAR(255),
    address VARCHAR(255)

);

CREATE TABLE Courier
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    phoneNumber  VARCHAR(15),
    statusOrder VARCHAR(255),
    coordinate   VARCHAR(255)
);
-- Создание таблицы "Рестораны"
CREATE TABLE Restaurants
(
    id                SERIAL PRIMARY KEY,
    name_restaurant   VARCHAR(255),
    address           VARCHAR(255),
    Status_restaurant VARCHAR(255)

);
-- Создание таблицы "Заказы"
CREATE TABLE Orders
(
    id              SERIAL PRIMARY KEY,
    id_Customer     INT REFERENCES Customers (id),
    id_Restaurants  INT REFERENCES Restaurants (id),
    statusOrder VARCHAR(255),
    id_Courier      INT REFERENCES Courier (ID),
    OrderTime       TIMESTAMP
);
CREATE TABLE Restaurant_Menu_Items
(
    id            SERIAL PRIMARY KEY,
    id_restaurant INT REFERENCES Restaurants (ID),
    name_items    VARCHAR(255),
    price         DECIMAL(10, 2),
    description   VARCHAR(255),
    image         VARCHAR(255)
);
-- Создание таблицы "Заказ_айтемы"
CREATE TABLE Order_Items
(
    id                       SERIAL PRIMARY KEY,
    id_Order                 INT REFERENCES Orders (id),
    id_Restaurant_Menu_Items INT REFERENCES Restaurant_Menu_Items (id),
    price                    DECIMAL(10, 2),
    quantity                 INT
);

-- Создание таблицы "Меню_айтемы"

