package ru.liga.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.DTO.RestaurantDto;
import ru.liga.DTO.RestourantMenuItemDTO;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.KitchenService;
import ru.liga.services.MenuItemsService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/kitchen")
@Slf4j
public class KithcenController {

    private final KitchenService kitchenService;
    private final MenuItemsService menuItemsService;


        /**
         * Принимает заказ по его идентификатору.
         *
         * @param orderId Идентификатор заказа.
         * @return Ответ о принятии заказа.
         */
        @Operation(
                summary = "Принять заказ",
                description = "Принимает заказ по его идентификатору и отправляет уведомление."
        )
        @PutMapping("/acceptOrder/{orderId}")
        public String acceptOrder(
                @RequestParam Long orderId
        ) {
            log.info("Запрос на принятие заказа по идентификатору: {}", orderId);
            kitchenService.acceptOrder(orderId);
            return "Заказ номер:" + orderId + " принят рестораном";
        }

        /**
         * Отменяет заказ по его идентификатору.
         *
         * @param orderId Идентификатор заказа.
         * @return Ответ об отмене заказа.
         */
        @Operation(
                summary = "Отменить заказ",
                description = "Отменяет заказ по его идентификатору и отправляет уведомление."
        )
        @PutMapping("/deniedOrder/{orderId}")
        public String deniedOrder(
                @RequestParam Long orderId
        ) {
            log.info("Запрос на отмену заказа по идентификатору: {}", orderId);
            kitchenService.deniedOrder(orderId);
            return "Заказ номер:" + orderId + " отменен рестораном";
        }
    @Operation(
            summary = "Заказ готов",
            description = "Меняет статус заказа на приготовлен и отправляет уведомление."
    )
        @PutMapping("/orderIsDone/{orderId}")
        public String orderIsDone(
                @RequestParam Long orderId
        ) {
            log.info("Запрос: заказ готов заказа по идентификатору: {}", orderId);
            kitchenService.orderIsDone(orderId);
            return "Заказ номер:" + orderId + " готов, ищем курьера";
        }

        /**
         * Открывает ресторан для онлайн заказов.
         *
         * @param orderId Идентификатор ресторана.
         * @return Сообщение об открытии ресторана.
         */
        @Operation(
                summary = "Открыть ресторан",
                description = "Открывает ресторан для онлайн заказов."
        )
        @PutMapping("/openRestaurant/{orderId}")
        public String openRestaurant(
                @RequestParam Long orderId
        ) {
            log.info("Запрос на открытие ресторана: {}", orderId);
            kitchenService.kitchenIsOpen(orderId);
            return "Ресторан открыт и готов принимать онлайн заказы";
        }

        /**
         * Закрывает ресторан для онлайн заказов.
         *
         * @param orderId Идентификатор ресторана.
         * @return Сообщение о закрытии ресторана.
         */
        @Operation(
                summary = "Закрыть ресторан",
                description = "Закрывает ресторан для онлайн заказов."
        )
        @PutMapping("/closeRestaurant/{orderId}")
        public String closeRestaurant(
                @RequestParam Long orderId
        ) {
            log.info("Запрос на закрытие ресторана: {}", orderId);
            kitchenService.kitchenIsClose(orderId);
            return "Ресторан закрыт и не принимает онлайн заказы";
        }

        /**
         * Создает новый ресторан.
         *
         * @param restaurant Данные о новом ресторане.
         * @return Созданный ресторан.
         */
        @Operation(
                summary = "Создать ресторан",
                description = "Создает новый ресторан."
        )
        @PostMapping("/createRestaurant")
        public ResponseEntity<RestaurantDto> createRestaurant(
                @RequestBody RestaurantDto restaurant
        ) {
            ;
            return ResponseEntity.ok(kitchenService.createRestaurant(restaurant));
        }

        /**
         * Создает новый элемент меню для ресторана.
         *
         * @param restaurantMenuItem Данные о новом элементе меню.
         * @param id Идентификатор ресторана.
         * @return Созданный элемент меню.
         */
        @Operation(
                summary = "Создать элемент меню",
                description = "Создает новый элемент меню для ресторана."
        )
        @PostMapping("/createMenuItems{id}")
        public ResponseEntity<RestaurantMenuItem> createMenuItems(
                @RequestBody RestourantMenuItemDTO restaurantMenuItem,
                @RequestParam Long id
        ) {
            RestaurantMenuItem createdMenuItem = menuItemsService.createMenuItem(id, restaurantMenuItem);
            return ResponseEntity.ok(createdMenuItem);
        }

        /**
         * Удаляет элемент меню по его идентификатору.
         *
         * @param id Идентификатор элемента меню.
         * @return Сообщение об удалении элемента меню.
         */
        @Operation(
                summary = "Удалить элемент меню",
                description = "Удаляет элемент меню по его идентификатору."
        )
        @DeleteMapping("/deleteMenuItems")
        public ResponseEntity<String> deleteMenuItems(
                @RequestParam Long id
        ) {
            menuItemsService.deleteMenuItem(id);
            return ResponseEntity.ok("Позиция была удалена из меню");
        }

        /**
         * Получает информацию о ресторане по его идентификатору.
         *
         * @param restaurantId Идентификатор ресторана.
         * @return Информация о ресторане.
         */
        @Operation(
                summary = "Получить ресторан по идентификатору",
                description = "Получает информацию о ресторане по его идентификатору."
        )
        @GetMapping("/{restaurantId}")
        public ResponseEntity<Restaurant> getRestaurantById(
                @PathVariable Long restaurantId
        ) {
            Restaurant restaurant = kitchenService.getRestaurantById(restaurantId);
            return ResponseEntity.ok(restaurant);
        }

        /**
         * Обновляет информацию о ресторане.
         *
         * @param restaurantId Идентификатор ресторана.
         * @param updatedRestaurant Обновленные данные о ресторане.
         * @return Обновленная информация о ресторане.
         */
        @Operation(
                summary = "Обновить ресторан",
                description = "Обновляет информацию о ресторане."
        )
        @PutMapping("/{restaurantId}")
        public ResponseEntity<Restaurant> updateRestaurant(
                @PathVariable Long restaurantId,
                @RequestBody Restaurant updatedRestaurant
        ) {
            Restaurant restaurant = kitchenService.updateRestaurant(restaurantId, updatedRestaurant);
            return ResponseEntity.ok(restaurant);
        }

        /**
         * Удаляет ресторан по его идентификатору.
         *
         * @param restaurantId Идентификатор ресторана.
         * @return Ответ об удалении ресторана.
         */
        @Operation(
                summary = "Удалить ресторан",
                description = "Удаляет ресторан по его идентификатору."
        )
        @DeleteMapping("/{restaurantId}")
        public ResponseEntity<Void> deleteRestaurant(
                @PathVariable Long restaurantId
        ) {
            kitchenService.deleteRestaurant(restaurantId);
            return ResponseEntity.noContent().build();
        }


}
