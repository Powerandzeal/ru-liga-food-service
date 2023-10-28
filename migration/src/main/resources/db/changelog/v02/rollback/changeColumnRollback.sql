-- Откат изменения для Orders
ALTER TABLE Orders
    ALTER COLUMN id_Courier DROP DEFAULT;

-- Откат изменения для Customers (удаление ограничения уникальности для столбца phone)
ALTER TABLE Customers
DROP CONSTRAINT unique_customer_phone;

-- Откат изменения для Order_Items (удаление проверок цен и количества)
ALTER TABLE Order_Items
DROP CONSTRAINT positive_price;
ALTER TABLE Order_Items
DROP CONSTRAINT non_negative_quantity;
