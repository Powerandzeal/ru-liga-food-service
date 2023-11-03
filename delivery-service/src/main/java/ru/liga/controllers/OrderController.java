package ru.liga.controllers;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
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
    @PutMapping("/pickingDelivery")
    public ResponseEntity<Orders> pickingOrder(@RequestParam Long orderId,@RequestParam Long courierId) {
        return ResponseEntity.ok(courierService.deliveryPicking(orderId,courierId));
    }
    @PutMapping("/deliveryIsDeliviring")
    public ResponseEntity<Orders> deliveryIsDeliviring(Long orderId) {
        return ResponseEntity.ok(courierService.deliveryIsDelivering(orderId));
    }

    @PutMapping("/finishedOrder")
    public ResponseEntity<Orders> finishedOrder(Long orderId) {
        return ResponseEntity.ok(courierService.deliveryIsFinished(orderId));
    }
    @PutMapping("/deniedOrder")
    public ResponseEntity<Orders> deniedOrder(Long orderId) {
        return ResponseEntity.ok(courierService.deniedDelivery(orderId));
    }


}
