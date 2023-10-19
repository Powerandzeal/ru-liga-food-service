package ru.liga.models;

import lombok.Data;
import org.hibernate.criterion.Order;
import ru.liga.StatusCurrier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Courier {
    @Id
    private Long id;
    private String name;
    private int phoneNumber;
    private String coordinates;
    private StatusCurrier statusCurrier;
    @OneToMany
    private List<Orders> orders;


}
