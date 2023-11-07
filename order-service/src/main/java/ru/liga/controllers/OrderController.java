package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.CreateOrderDTO;
import ru.liga.DTO.ResponseOrderDTO;
import ru.liga.DTO.RestourantMenuDTO;
import ru.liga.DTO.RestourantMenuItemDTO;
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

    /**
     * Создание нового заказа для заказчика по его идентификатору.
     *
     * @param createOrderDTO Данные нового заказа.
     * @param customerId     Идентификатор заказчика.
     * @return Ответ с информацией о созданном заказе.
     */
    @Operation(
            summary = "Создать заказ",
            description = "Создает новый заказ для заказчика."
    )
    @PostMapping("/createOrder{customerId}")
    public ResponseEntity<ResponseOrderDTO> createOrder(
            @RequestBody CreateOrderDTO createOrderDTO, @RequestParam Long customerId
    ) {
        String message = "запрос на создание заказа";
        log.info("createOrderFrom customer id = " + customerId);

        ResponseOrderDTO responseOrderDTO = orderService.createOrder(createOrderDTO, customerId);
        serviceFeignClient.sendNotification(responseOrderDTO);
        return ResponseEntity.ok(responseOrderDTO);
    }

    /**
     * Получение списка всех ресторанов.
     *
     * @return Список ресторанов.
     */
    @Operation(
            summary = "Получить список ресторанов",
            description = "Получает список всех ресторанов."
    )
    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {
        return ResponseEntity.ok(orderService.getAllRestaurant());
    }

    /**
     * Найти ресторан по его идентификатору.
     *
     * @param id Идентификатор ресторана.
     * @return Информация о найденном ресторане.
     */
    @Operation(
            summary = "Найти ресторан по идентификатору",
            description = "Находит ресторан по его идентификатору."
    )
    @GetMapping("/findByIdRestouarnt{id}")
    public ResponseEntity<RestourantMenuDTO> findByIdRestouarnt(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(orderService.findByIdRestaurant(id));
    }

    /**
     * Получение меню ресторана по его идентификатору.
     *
     * @param id Идентификатор ресторана.
     * @return Меню ресторана.
     */
    @Operation(
            summary = "Получить меню ресторана",
            description = "Получает меню ресторана по его идентификатору."
    )
    @GetMapping("/getMenu{id}")
    public ResponseEntity<List<RestaurantMenuItem>> getMenu(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(orderService.getMenu(id));
    }

    /**
     * Получение второго меню ресторана по его идентификатору.
     *
     * @param id Идентификатор ресторана.
     * @return Второе меню ресторана.
     */
    @Operation(
            summary = "Получить второе меню ресторана",
            description = "Получает второе меню ресторана по его идентификатору."
    )
    @GetMapping("/getMenu2{id}")
    public ResponseEntity<List<RestourantMenuItemDTO> >getMenu2(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(orderService.getMenu2(id));
    }

    /**
     * Получение заказа по его идентификатору.
     *
     * @param id Идентификатор заказа.
     * @return Информация о заказе.
     */
    @Operation(
            summary = "Получить заказ по идентификатору",
            description = "Получает заказ по его идентификатору."
    )
    @GetMapping("/getOrderById{id}")
    public ResponseEntity<Orders> getOrderById(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    /**
     * Оплата заказа по его идентификатору.
     *
     * @param orderId Идентификатор заказа для оплаты.
     * @return Сообщение об успешной оплате заказа.
     */
    @Operation(
            summary = "Оплатить заказ",
            description = "Оплачивает заказ по его идентификатору."
    )
    @PutMapping("/pay{id}")
    public ResponseEntity<String> payOrder(
            @RequestParam Long orderId
    ) {
        return ResponseEntity.ok(orderService.payOrder(orderId));
    }

    /**
     * Отмена заказа по его идентификатору.
     *
     * @param orderId Идентификатор заказа для отмены.
     * @return Сообщение об успешной отмене заказа.
     */
    @Operation(
            summary = "Отменить заказ",
            description = "Отменяет заказ по его идентификатору."
    )
    @PutMapping("/cancelOrder{id}")
    public ResponseEntity<String> cancelOrder(
            @RequestParam Long orderId
    ) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
    @PostMapping("/send")
    public void sendMessageToRabbit(@RequestBody ResponseOrderDTO messageModel) {
        orderService.sendOrder(messageModel);
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
