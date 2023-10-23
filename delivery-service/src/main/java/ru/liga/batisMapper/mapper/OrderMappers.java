package ru.liga.batisMapper.mapper;


import org.apache.ibatis.annotations.Mapper;
import ru.liga.models.Orders;

import java.util.List;
@Mapper
public interface OrderMappers {
    List<Orders> getOrdersByStatuss(String status);
    Orders getOrderById(Long id);

}
