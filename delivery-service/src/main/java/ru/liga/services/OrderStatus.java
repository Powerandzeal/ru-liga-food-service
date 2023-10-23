package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.models.Orders;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderStatus {

    private final OrderMapper orderMapper;
    public Orders getOrderById (Long id){
      return  orderMapper.getOrderById(id);
    }
    public List<Orders> getOrderByStatus (String status){
        return  orderMapper.getOrdersByStatuss(status);
    }
}
