-- Создание таблицы "Customers"
CREATE TABLE Customers (
                           ID SERIAL PRIMARY KEY,
                           phone VARCHAR(15),
                           E_mail VARCHAR(255),
                           adress VARCHAR(255)
);

CREATE TABLE Currier (
                        ID SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        phone VARCHAR(15),
                        Order_Status VARCHAR(255),
                        Координаты POINT
);
-- Создание таблицы "Рестораны"
CREATE TABLE Restaurants (
                             ID SERIAL PRIMARY KEY,
                             adress VARCHAR(255),
                             Status_restaurant VARCHAR(255)
);
-- Создание таблицы "Заказы"
CREATE TABLE Orders (
                        ID SERIAL PRIMARY KEY,
                        ID_Customer INT REFERENCES Customers(ID),
                        ID_Restaurants INT REFERENCES Restaurants(ID),
                        ID_Order_Status INT,
                        ID_Currier INT REFERENCES Currier(ID),
                        OrderTime TIMESTAMP
);
CREATE TABLE Restaurant_Menu_Items (
                                       ID SERIAL PRIMARY KEY,
                                       ID_ресторана INT REFERENCES Restaurants(ID),
                                       Название VARCHAR(255),
                                       price DECIMAL(10, 2),
                                       description TEXT,
                                       image VARCHAR(255)
);
-- Создание таблицы "Заказ_айтемы"
CREATE TABLE Order_Items (
                              ID SERIAL PRIMARY KEY,
                              ID_Order INT REFERENCES Orders(ID),
                              ID_Restaurant_Menu_Items INT REFERENCES Restaurant_Menu_Items(ID),
                              quantity INT
);

-- Создание таблицы "Меню_айтемы"

