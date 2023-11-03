package ru.liga.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.DTO.NotificationAcceptOrderByKitchen;
import ru.liga.Enum.KitchenStatusOrder;
import ru.liga.Enum.OrderStatus;
import ru.liga.models.Orders;
import ru.liga.models.Restaurant;
import ru.liga.repositories.OrdersRepository;
import ru.liga.repositories.RestaurantRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {
    private final RestaurantRepository restaurantRepository;
    private final OrdersRepository ordersRepository;
    private final RabitMqProducerKitchen rabitMqProducerKitchen;
    private final ObjectMapper objectMapper;
    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        return updatedRestaurant;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
       return  restaurantRepository.findById(restaurantId).orElseThrow();
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
    public void acceptOrder (Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow();
        order.setOrderStatus(OrderStatus.KITCHEN_ACCEPTED);
        rabitMqProducerKitchen.sendNotificationCustomer("Ваш заказ №:"+id+" был принят рестораном и " +
                "начал приготавливаться","order.customer");
        ordersRepository.save(order);
    }
    public String sendOrder(NotificationAcceptOrderByKitchen order) {
        String carInfoInLine = tryToSerialyzeMessageAsString(order);
        if (order.getCustomerId()> 0) {
            rabitMqProducerKitchen.sendNotificationCustomer(carInfoInLine, "order.it");
        }
        return "Cообщение отправлено";
    }
    public String tryToSerialyzeMessageAsString(NotificationAcceptOrderByKitchen notification) {
        String carInfoInLine = null;
        try {
            carInfoInLine = objectMapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return carInfoInLine;
    }
    public void deniedOrder(Long id) {
        Orders order =  ordersRepository.findById(id).orElseThrow();
        System.out.println(order);
        order.setOrderStatus(OrderStatus.KITCHEN_DENIED);
        rabitMqProducerKitchen.sendNotificationCustomer("Ваш заказ №:"+id+" был отменен рестораном","order.customer");

        ordersRepository.save(order);

    }
    public void orderIsDone (Long id) {
        Orders order =  ordersRepository.findById(id).orElseThrow();
        System.out.println(order);
        order.setOrderStatus(OrderStatus.KITCHEN_PREPARING);
        rabitMqProducerKitchen.sendNotificationCustomer("Ваш заказ №:"+id+" Готов и ожидает курьера","order.customer");
        ordersRepository.save(order);

    }
    public void kitchenIsOpen(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setKitchenStatusOrder(KitchenStatusOrder.KITCHEN_IS_OPEN);
        restaurantRepository.save(restaurant);
    }
    public void kitchenIsClose(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setKitchenStatusOrder(KitchenStatusOrder.KITCHEN_IS_CLOSED);
        restaurantRepository.save(restaurant);
    }
}
