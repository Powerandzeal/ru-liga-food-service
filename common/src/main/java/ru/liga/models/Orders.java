package ru.liga.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_customer")
    private int customerId;
    @Column(name = "id_restaurants")
    private int restaurantId;
    @Column(name = "statusorder")
    private String statusOrder;
    @Column(name = "id_courier")
    private int currierId;
    @Column(name = "ordertime")
    private Timestamp timeDelivery;
}
