package ru.liga.batisMapper;


import org.apache.ibatis.annotations.Mapper;
import ru.liga.models.Orders;

import java.util.List;
@Mapper
public interface OrderMapper {
    List<Orders> getOrdersByStatuss(String status);
    Orders getOrderById(Long id);

}
