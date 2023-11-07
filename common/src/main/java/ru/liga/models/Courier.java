package ru.liga.models;

import lombok.Data;
import ru.liga.Enum.DeliveryStatusOrder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "statusorder")
    @Enumerated(EnumType.STRING)
    private DeliveryStatusOrder statusOrder;

    @Column(name = "coordinate")
    private String coordinate;

//    @OneToMany(mappedBy = "courier")
//    private List<Orders> orders;


}
