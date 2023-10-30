package ru.liga.dto;

import lombok.Data;
import ru.liga.Enum.KitchenStatusOrder;
import ru.liga.models.RestaurantMenuItem;

import java.util.List;

@Data
public class RestourantMenuDTO {
    String name;
    String address;
    List<RestourantMenuItemDTO> menu;
}
