package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.models.Courier;
import ru.liga.models.Orders;
import ru.liga.services.CourierService;
import ru.liga.services.OrderService;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private CourierService courierService;


    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(Orders order) {
      return ResponseEntity.ok(orderService.createOrder(order))  ;
    }
    @GetMapping("/{status}")
    public ResponseEntity <List<Orders>> getOrderByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderService.getOrderByStatus(status));
    }
    @GetMapping("/getCourierByStatus2")
    public ResponseEntity <List<Orders>> getOrderByStatus2(@RequestParam String status) {
        return ResponseEntity.ok(courierService.getOrderByStatus2(status));
    }
    @GetMapping("/fingById")
    public ResponseEntity <Orders> getOrderById(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("updateOrder")
    public ResponseEntity<Orders> updateOrder(Orders orders) {
        orderService.createOrder(orders);
        return null;
    }
    @DeleteMapping
    public ResponseEntity<?> createOrder(Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Заказ был удален")  ;
    }
}
