package ru.liga.dto.Courier;

import lombok.Data;

@Data
public class CreateCourierDTO {
    private String name;
    private String phonenumber;
    private String coordinate;
}
