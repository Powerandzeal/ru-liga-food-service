package ru.liga.models;

import javax.persistence.Column;
import javax.persistence.Id;

public class Order {
    @Id
    private Long id;
    @Column
    private Long customerId;
    @Column
    private Long restaurantId;
    @Column
    private String statusOrder;
    @Column
    private Long currierId;
    @Column
    private int timeDelivery;
}
