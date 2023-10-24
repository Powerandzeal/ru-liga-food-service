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
    @ManyToOne
    private Orders orderId;

    @Column(name = "id_restraunt_menu_items")
    @ManyToOne
    private RestaurantMenuItem restaurantMenuItemId;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;
}
