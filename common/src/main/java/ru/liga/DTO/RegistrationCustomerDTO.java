package ru.liga.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationCustomerDTO {

    @NotBlank
    String phone;

    @NotBlank
    String name;

    @NotBlank
    private String password;


    @NotBlank
    String email;

    @NotBlank
    String address;


}
