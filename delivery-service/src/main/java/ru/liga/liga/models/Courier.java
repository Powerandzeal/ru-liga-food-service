package ru.liga.liga.models;

import lombok.Data;
import org.springframework.core.annotation.Order;
import ru.liga.liga.StatusCurrier;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Courier {
    @Id
    Long id;
    String name;
    int phoneNumber;
    String coordinates;
    StatusCurrier statusCurrier;
//    Order order;




}
