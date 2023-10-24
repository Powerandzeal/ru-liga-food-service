package ru.liga.models;

import lombok.Data;
import ru.liga.Enum.DeliveryStatusOrder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_customer")
    @ManyToOne
    private Customers customerId;

    @Column(name = "id_restaurants")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Restaurant restaurantId;

    @Column(name = "statusorder")
    @Enumerated(EnumType.STRING)
    private DeliveryStatusOrder statusOrder;

    @Column(name = "id_courier")
    private int currierId;

    @Column(name = "ordertime")
    private Timestamp timeDelivery;

    @OneToMany
    List<OrderItems> orderItemsList;
}
