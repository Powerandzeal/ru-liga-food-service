package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.Enum.DeliveryStatusOrder;

import ru.liga.models.Orders;
import ru.liga.services.CourierService;
import ru.liga.services.OrderStatus;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final CourierService courierService;

    private final OrderStatus orderStatus;

//    @PostMapping
//    public ResponseEntity<Orders> createOrder(@ReCreateOrderDTO orderDTO) {
//        orderStatus.createOrder();
//        return null;
//    }

    @GetMapping("/getCourierByStatus")
    public ResponseEntity<List<Orders>> getOrderByStatus(@RequestParam String status) {
        return ResponseEntity.ok(orderStatus.getOrderByStatus(status));
    }

    @GetMapping("/getCourierByStatus2")
    public ResponseEntity<List<Orders>> getOrderByStatus2(@RequestParam DeliveryStatusOrder status) {
        return ResponseEntity.ok(courierService.getOrderByStatus2(status));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderStatus.getOrderById(orderId));
    }
}
