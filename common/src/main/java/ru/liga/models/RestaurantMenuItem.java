package ru.liga.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {
    @Id
    Long id;

    @Column(name= "id_restaurant")
    @ManyToOne
    Restaurant restaurantId;

    @Column(name= "name_items")
    String name;

    @Column(name= "price")
    double price;

    @Column(name= "description")
    byte [] image;

    @Column(name= "image")
    String description;
}
