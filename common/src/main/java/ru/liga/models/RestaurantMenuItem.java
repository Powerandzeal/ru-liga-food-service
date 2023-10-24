package ru.liga.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table
public class RestaurantMenuItem {
    @Id
    Long id;

    @Column(name= "id_restaurant")
    int restaurantId;

    @Column(name= "namt_items")
    String name;

    @Column(name= "price")
    int price;

    @Column(name= "description")
    byte image;

    @Column(name= "image")
    String description;
}
