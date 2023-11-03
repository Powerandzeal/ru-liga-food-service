package ru.liga.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationCustomerDTO {
    @NotBlank
    String name;

    @NotBlank
    String phone;

    @NotBlank
    String email;

    @NotBlank
    String address;

}
