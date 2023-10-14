package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RestaurantMenuItems {
    @Id
    Long id;
    int restaurantId;
    String name;
    int price;
    byte image;
    String description;
}
