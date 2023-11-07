package ru.liga.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="restaurant_menu_item_id" )
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    Restaurant restaurant;

    @Column(name= "name_items")
    String name;

    @Column(name= "price")
    double price;

//    @Lob // Это аннотация для больших объектов (large objects)
//    @Basic(fetch = FetchType.LAZY) // Добавляем fetch-стратегию, если это подходит для вашего случая
//    @Column(name= "image", columnDefinition = "bytea")
//    byte[] image;

    @Column(name= "description")
    String description;


}
