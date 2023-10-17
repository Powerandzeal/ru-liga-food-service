package ru.liga.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        Order createdOrder = orderService.createOrder(order);
//        return ResponseEntity.ok(createdOrder);
//    }
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
