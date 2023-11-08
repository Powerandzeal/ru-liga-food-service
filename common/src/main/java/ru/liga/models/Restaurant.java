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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    Long id;

    @Column(name = "name_restaurant")
    String nameRestaurant;

    @Column(name = "address")
    String addressKitchen;

    @Column(name = "status_restaurant")
    @Enumerated(EnumType.STRING)
    KitchenStatusOrder kitchenStatusOrder;

    @OneToOne
    @JoinColumn(name = "restaurant_user_id")
    private Users users;

//    @OneToMany(mappedBy = "restaurant")
//    List<RestaurantMenuItem> restaurantMenuItem;
}
