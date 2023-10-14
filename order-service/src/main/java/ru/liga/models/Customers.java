package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customers {
    @Id
    private Long id;
    private int phone;
    private String email;
    private String address;
}
