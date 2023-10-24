package ru.liga.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class RestaurantMenuItem {
    @Id
    Long id;

    @Column(name= "id_restaurant")
            @ManyToOne
    Restaurant restaurantId;

    @Column(name= "namt_items")
    String name;

    @Column(name= "price")
    int price;

    @Column(name= "description")
    byte [] image;

    @Column(name= "image")
    String description;
}
