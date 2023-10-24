package ru.liga.dto.Courier;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateCourierDTO {
    private String name;
    private String phonenumber;
    private String coordinate;
}
