package ru.liga.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.StatusOrder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int phonenumber;
    private String coordinate;
    private String order_status;
//    @OneToMany
//    private List<Orders> orders;


}
