package ru.liga.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class NotificationOrdersForCourier {
    Long orderId;
    String nameRestaurant;
    String adressRestaurant;
    double income;
    Timestamp timeDelivery;
    String addresCustomer;
}
