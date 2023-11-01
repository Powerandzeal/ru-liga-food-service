package ru.liga.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CreateOrderDTO;
import ru.liga.dto.ResponseOrderDTO;
import ru.liga.dto.RestourantMenuDTO;
import ru.liga.dto.RestourantMenuItemDTO;
import ru.liga.models.Orders;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.OrderService;
import ru.liga.services.ServiceFeignClient;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final AmqpTemplate amqpTemplate;

    private final OrderService orderService;

    private final ServiceFeignClient serviceFeignClient;
    @PostMapping("/createOrder{customerId}")
    public ResponseEntity<ResponseOrderDTO> createOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestParam Long customerId) {
        String message = "запрос на создание заказа";
        log.info("createOrderFrom customer id = " + customerId);

//        serviceFeignClient.sendNotification("Пользователь id= " +customerId+ "сделали заказ =" );
//        amqpTemplate.convertAndSend("myQueue",message);
        ResponseOrderDTO responseOrderDTO = orderService.createOrder(createOrderDTO,customerId);
        serviceFeignClient.sendNotification(responseOrderDTO);
        return ResponseEntity.ok(responseOrderDTO);

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
    @GetMapping("/getOrderById{id}")
    public ResponseEntity<Orders> getOrderById(@RequestParam Long id) {

        return ResponseEntity.ok( orderService.getOrderById(id));
    }
    @PutMapping("/pay{id}")
    public ResponseEntity<String > payOrder(@RequestParam Long orderId) {

        return ResponseEntity.ok( orderService.payOrder(orderId));
    }
    @PutMapping("/cancelOrder{id}")
    public ResponseEntity<String > cancelOrder(@RequestParam Long orderId) {

        return ResponseEntity.ok( orderService.cancelOrder(orderId));
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
