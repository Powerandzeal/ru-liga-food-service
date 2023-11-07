package ru.liga.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="id_order")
    private Orders orderEntity;

    @OneToOne
    @JoinColumn(name = "id_restaurant_menu_items")
    private RestaurantMenuItem restaurantMenuItem;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;
}
