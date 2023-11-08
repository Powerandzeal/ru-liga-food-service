package ru.liga.DTO;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCourierDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String phonenumber;
    @NotBlank
    private String password;
    @NotBlank
    private String coordinate;
}
