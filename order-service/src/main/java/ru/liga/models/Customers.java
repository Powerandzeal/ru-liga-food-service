package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class Customers {
    @Id
    private Long id;
    private int phone;
    private String email;
    private String address;
    @OneToMany
    private List<Order> order;
}
