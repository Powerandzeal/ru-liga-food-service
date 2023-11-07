package ru.liga.DTO;

import lombok.Data;
import ru.liga.DTO.RestourantMenuItemDTO;

import java.util.List;

@Data
public class RestourantMenuDTO {
    String name;
    String address;
    List<RestourantMenuItemDTO> menu;
}
