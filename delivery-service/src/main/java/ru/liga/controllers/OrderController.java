package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.NotificationOrdersForCourier;

import ru.liga.models.Orders;
import ru.liga.services.CourierService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final CourierService courierService;


    /**
     * Получает все заказы доступные для курьера  .
     *
     * @return возвращает заказы курьера .
     */
    @Operation(
            summary = "Список свободных заказов",
            description = "Выдает список заказов которые можно взять."
    )
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @GetMapping("/getOrdersForCourier{id}")
    public ResponseEntity<List<NotificationOrdersForCourier>> getFreeOrders() {
        log.info("Received request to get free orders ");

        List<NotificationOrdersForCourier> orders = courierService.getFreeOrders();
        return ResponseEntity.ok(orders);
    }


    /**
     * Меняется стутус заказа  как взятый курьером.
     *
     * @param orderId ID заказа.
     *                //     * @param courierId ID курьера.
     * @return Информация о заказе.
     */
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @Operation(summary = "Взять заказ", description = "Отметить заказ как взятый курьером и назначить курьера.")
    @PutMapping("/pickingDelivery")
    public ResponseEntity<Orders> pickingOrder(
            @RequestParam Long orderId,
            Authentication authentication
    ) {
        log.info("Received request to pickOrder from");

        return ResponseEntity.ok(courierService.deliveryPicking(orderId, authentication));
    }

    /**
     * Отметить заказ как доставляемый.
     *
     * @param orderId ID заказа.
     * @return Информация о заказе.
     */

    /**
     * Отметить заказ как завершенный.
     *
     * @param orderId ID заказа.
     * @return Информация о заказе.
     */
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @Operation(summary = "Отметить заказ как завершенный", description = "Отметить заказ как завершенный.")
    @PutMapping("/finishedOrder")
    public ResponseEntity<Orders> finishedOrder(
            @RequestParam Long orderId
    ) {
        log.info("Received request to get free orders from сourier");

        return ResponseEntity.ok(courierService.deliveryIsFinished(orderId));
    }

    /**
     * Отменить заказ.
     *
     * @param orderId ID заказа.
     * @return Информация о заказе.
     */
    @PreAuthorize("hasRole('ROLE_COURIER')")
    @Operation(summary = "Отменить заказ", description = "Отменить заказ и вернуть информацию о заказе.")
    @PutMapping("/deniedOrder")
    public ResponseEntity<Orders> deniedOrder(
            @RequestParam Long orderId) {
        return ResponseEntity.ok(courierService.deniedDelivery(orderId));
    }


}
