package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@Entity
public class Order {
    @Id
    private Long id;
    private int customerId;
    private int restaurantId;
    private String statusOrder;
    private int currierId;
    private int timeDelivery;
}
