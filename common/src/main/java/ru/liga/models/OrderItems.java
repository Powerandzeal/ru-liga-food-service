package ru.liga.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_order")
    private int orderId;

    @Column(name = "id_restraunt_menu_items")
    private int restaurantMenuItemId;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;
}
