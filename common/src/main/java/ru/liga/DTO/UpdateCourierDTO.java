package ru.liga.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateCourierDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phonenumber;
    @NotBlank
    private String coordinate;
}
