package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class OrderItem {
    @Id
    private Long id;
    private int orderId;
    private int restaurantMenuItemId;
    private int price;
    private int quantity;
}
