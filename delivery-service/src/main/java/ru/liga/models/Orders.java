package ru.liga.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Orders {
    @Id
    private Long id;


}
