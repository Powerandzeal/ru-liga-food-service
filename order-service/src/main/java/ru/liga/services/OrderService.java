package ru.liga.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.models.Order;
import ru.liga.repositoryes.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
   private final OrderRepository orderRepository;
    public Order createOrder(Order order) {

        return orderRepository.save(order);
    }
    public void changeOrder() {

    }
    public void getOrder() {

    }
    public void deleteOrder() {

    }
}
