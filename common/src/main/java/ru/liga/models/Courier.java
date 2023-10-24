package ru.liga.models;

import lombok.Data;
import ru.liga.Enum.DeliveryStatusOrder;

import javax.persistence.*;

@Data
@Entity
@Table(name = "courier")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "statusorder")
    private DeliveryStatusOrder statusOrder;
    @Column(name = "coordinate")
    private String coordinate;

//    @OneToMany
//    private List<Orders> orders;


}
