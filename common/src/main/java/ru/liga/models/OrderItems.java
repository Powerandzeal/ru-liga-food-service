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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Orders order;


    @OneToOne
    @Column(name = "id_restaurant_menu_items")
    private RestaurantMenuItem restaurantMenuItemId;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;
}
