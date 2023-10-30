package ru.liga.dto;

import java.sql.Timestamp;
import java.util.List;

public class ResponseOrderDTO {
    Long orderId;
    String nameRestaurant;
    List<RestourantMenuItemDTO> listOrder;
    double orderPrice;
    Timestamp timeCreate;



}
