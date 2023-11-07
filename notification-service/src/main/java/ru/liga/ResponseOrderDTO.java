package ru.liga;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class ResponseOrderDTO {
    Long orderId;
    String nameRestaurant;
    List<RestourantMenuItemDTO> listOrder;
    double orderPrice;
    Timestamp timeCreate;



}
