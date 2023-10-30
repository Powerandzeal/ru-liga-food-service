package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.dto.RestourantMenuDTO;
import ru.liga.dto.RestourantMenuItemDTO;
import ru.liga.models.Orders;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/createOrder{customerId}")
    public ResponseEntity<Orders> createOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestParam Long customerId) {

        return ResponseEntity.ok(orderService.createOrder(createOrderDTO,customerId));
    }
    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {

        return ResponseEntity.ok( orderService.getAllRestaurant());
    }
    @GetMapping("/findByIdRestouarnt{id}")
    public ResponseEntity<RestourantMenuDTO> findByIdRestouarnt(@RequestParam Long id) {

        return ResponseEntity.ok( orderService.findByIdRestaurant(id));
    }
    @GetMapping("/getMenu{id}")
    public ResponseEntity<List<RestaurantMenuItem>> getMenu(@RequestParam Long id) {

        return ResponseEntity.ok( orderService.getMenu(id));
    }
    @GetMapping("/getMenu2{id}")
    public ResponseEntity<List<RestourantMenuItemDTO>> getMenu2(@RequestParam Long id) {

        return ResponseEntity.ok( orderService.getMenu2(id));
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
