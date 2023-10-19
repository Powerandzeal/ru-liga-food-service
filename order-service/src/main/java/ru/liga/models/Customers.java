package ru.liga.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String email;
    private String address;
//    @OneToMany
//    private List<Order> order;
}
