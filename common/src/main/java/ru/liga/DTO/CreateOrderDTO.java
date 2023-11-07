package ru.liga.DTO;


import lombok.Data;
import ru.liga.DTO.ItemsDto;
import ru.liga.models.OrderItems;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateOrderDTO {

    private Long restaurantId;
    private List<ItemsDto> items;
}
