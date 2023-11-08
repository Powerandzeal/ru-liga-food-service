package ru.liga.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.liga.Enum.DeliveryStatusOrder;
import ru.liga.Enum.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @Column(name = "order_id",unique = true)
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "id_restaurants")
    private Restaurant restaurant;

    @Column(name = "statusorder")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "id_courier")
    private Courier courier;

    @Column(name = "create_time")
    private Timestamp createOrderTime;

    @Column(name = "ordertime")
    private Timestamp timeDelivery;

//    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderItems> orderItems;

    @Column(name= "price")
    double price;
}
