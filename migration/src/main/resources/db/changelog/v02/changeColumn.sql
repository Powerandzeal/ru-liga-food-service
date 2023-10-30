-- ALTER TABLE Orders
--     ALTER COLUMN id_Courier SET DEFAULT NULL;
ALTER TABLE Customers
    ADD CONSTRAINT unique_customer_phone UNIQUE (phone);
-- ALTER TABLE Order_Items
--     ADD CHECK  (price >= 0 AND quantity >= 0);
