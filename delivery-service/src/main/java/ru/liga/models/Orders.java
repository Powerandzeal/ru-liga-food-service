package ru.liga.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Orders {
    @Id
    private Long id;
    @Column
    private int customerId;
    @Column
    private int restaurantId;
    @Column
    private String statusOrder;
    @Column
    private int currierId;
    @Column
    private int timeDelivery;
}
