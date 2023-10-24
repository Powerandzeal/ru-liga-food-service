package ru.liga.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    Long id;
    @Column(name= "name_restaurant")
    String nameKitchen;
    @Column(name= "address")
    String addressKitchen;
    @Column(name = "status_restaurant)")
    String status;
}
