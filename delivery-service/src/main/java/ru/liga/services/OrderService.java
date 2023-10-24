package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.models.Orders;
import ru.liga.repositoryes.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private final OrderMapper orderMapper;

    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }
    public Orders getOrderById (Long id){
      return  orderMapper.getOrderById(id);
    }

    public void deleteOrderById (Long id) {
        orderRepository.deleteById(id);
    }
    public List<Orders> getOrderByStatus (String status){
        return  orderMapper.getOrdersByStatuss(status);
    }
}
