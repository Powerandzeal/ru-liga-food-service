package ru.liga;

import lombok.Data;

@Data
public class RestourantMenuItemDTO {
    Long id;
    String name;
    double price;
    byte [] image;
    String description;

}
