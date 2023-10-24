package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Restaurant {
    @Id
    Long id;
    String nameKitchen;
    String addressKitchen;
    String status;
}
