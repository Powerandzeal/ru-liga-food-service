package ru.liga.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public String acceptOrder (Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow();
        order.setOrderStatus(OrderStatus.KITCHEN_ACCEPTED);
        ordersRepository.save(order);
        return "Заказ принят рестораном" ;
    }
    public String cancelOrder(Long id) {
        Orders order =  ordersRepository.findById(id).orElseThrow();
        System.out.println(order);
        order.setOrderStatus(OrderStatus.CUSTOMER_CANCELLED);
        ordersRepository.save(order);
        return "Заказ Отменен" ;
    }
    public void kitchenIsOpen() {

    }
    public void kitchenIsClose() {

    }
}
