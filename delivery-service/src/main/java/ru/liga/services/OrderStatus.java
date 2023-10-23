package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.models.Orders;

@Service
@AllArgsConstructor
public class OrderStatus {

    private final OrderMapper orderMapper;
    public Orders getOrderById (Long id){
      return  orderMapper.getOrderById(id);
    }
}
