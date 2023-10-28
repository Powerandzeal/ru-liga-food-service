package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.models.Orders;
import ru.liga.services.OrderService;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/{customerId}")
    public ResponseEntity<Orders> createOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestParam Long customerId) {

        return ResponseEntity.ok(orderService.createOrder(createOrderDTO,customerId));
    }
//
//    // Read (Retrieve) all
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }
//
//    // Read (Retrieve) by ID
//    @GetMapping("/{orderId}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
//        Order order = orderService.getOrderById(orderId);
//        return ResponseEntity.ok(order);
//    }
//
//    // Update
//    @PutMapping("/{orderId}")
//    public ResponseEntity<Order> updateOrder(
//            @PathVariable Long orderId,
//            @RequestBody Order updatedOrder
//    ) {
//        Order order = orderService.updateOrder(orderId, updatedOrder);
//        return ResponseEntity.ok(order);
//    }
//
//    // Delete
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
//        orderService.deleteOrder(orderId);
//        return ResponseEntity.noContent().build();
//    }

}
