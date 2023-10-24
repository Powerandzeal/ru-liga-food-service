package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.models.Orders;
import ru.liga.services.CourierService;
import ru.liga.services.OrderStatus;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    private CourierService courierService;
    @Autowired
    OrderStatus orderStatus;

    @GetMapping("/getCourierByStatus")
    public ResponseEntity <List<Orders>> getOrderByStatus(@RequestParam String status) {
        return ResponseEntity.ok(orderStatus.getOrderByStatus(status));
    }
    @GetMapping("/getCourierByStatus2")
    public ResponseEntity <List<Orders>> getOrderByStatus2(@RequestParam String status) {
        return ResponseEntity.ok(courierService.getOrderByStatus2(status));
    }
    @GetMapping("/getOrderById")
    public ResponseEntity <Orders> getOrderById(@RequestParam Long id) {
        return ResponseEntity.ok(orderStatus.getOrderById(id));
    }
}
