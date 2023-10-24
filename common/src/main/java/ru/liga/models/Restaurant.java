package ru.liga.models;

import lombok.Data;
import ru.liga.Enum.KitchenStatusOrder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    Long id;
    @Column(name = "name_restaurant")
    String nameKitchen;
    @Column(name = "address")
    String addressKitchen;
    @Column(name = "status_restaurant)")
    @Enumerated(EnumType.ORDINAL)
    KitchenStatusOrder kitchenStatusOrder;
    @Column(name = "restaurant_menu_items")
    @OneToMany
    List<RestaurantMenuItem> restaurantMenuItem;
}
