package ru.liga.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.liga.DTO.NotificationAcceptOrderByKitchen;
import ru.liga.DTO.NotificationOrdersForCourier;
import ru.liga.DTO.ResponseOrderDTO;
import ru.liga.DTO.RestaurantDto;
import ru.liga.Enum.KitchenStatusOrder;
import ru.liga.Enum.OrderStatus;
import ru.liga.config.Role;
import ru.liga.models.Orders;
import ru.liga.models.Restaurant;
import ru.liga.models.Users;
import ru.liga.repositories.OrdersRepository;
import ru.liga.repositories.RestaurantRepository;
import ru.liga.repositories.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {
    private final RestaurantRepository restaurantRepository;
    private final OrdersRepository ordersRepository;
    private final RabitMqProducerKitchen rabitMqProducerKitchen;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        return updatedRestaurant;
    }

    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {

        log.info("Создание нового ресторана");

        // Создаем и сохраняем пользователя
        Users users = new Users();
        users.setPassword(passwordEncoder.encode(restaurantDto.getPassword()));
        users.setUsername(restaurantDto.getNameRestaurant());
        users.setRoleUser(Role.RESTAURANT);
        users = userRepository.save(users);
    // Сохраняем и получаем пользователя с id
        Restaurant restaurant = new Restaurant();
        restaurant.setNameRestaurant(restaurantDto.getNameRestaurant());
        restaurant.setAddressKitchen(restaurantDto.getAddressKitchen());
        restaurant.setKitchenStatusOrder(KitchenStatusOrder.KITCHEN_IS_CLOSED);
        restaurant.setUsers(users); // Используем id пользователя
        restaurantRepository.save(restaurant);
        return restaurantDto;
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
        Instant currentInstant = Instant.now(); // Получение текущего времени
        Instant deliveryInstant = currentInstant.plus(50, ChronoUnit.MINUTES); // Добавление 50 минут к текущему времени

        order.setTimeDelivery(Timestamp.from(deliveryInstant));
       ; // Назначение времени доставки на 50 минут позже


        rabitMqProducerKitchen.sendNotificationCustomer("Ваш заказ №:"+id+" Готов и ожидает курьера","order.customer");
        sendOrder(order);
        ordersRepository.save(order);

    }
    public String sendOrder(Orders order) {
        NotificationOrdersForCourier notificationOrdersForCourier = new NotificationOrdersForCourier();
        notificationOrdersForCourier.setAddresCustomer(order.getCustomer().getAddress());
        notificationOrdersForCourier.setAdressRestaurant(restaurantRepository.findByNameRestaurant(order.getRestaurant().getNameRestaurant()).getAddressKitchen());
        notificationOrdersForCourier.setIncome((order.getPrice()/4));
        notificationOrdersForCourier.setTimeDelivery(order.getTimeDelivery());
        rabitMqProducerKitchen.sendNotificationForCouriers(tryToSerialyzeMessageAsString(notificationOrdersForCourier),"order.courier"); ;

        return "Cообщение отправлено";
    }
    public String tryToSerialyzeMessageAsString(NotificationOrdersForCourier orderDTO) {
        String carInfoInLine = null;
        try {
            carInfoInLine = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return carInfoInLine;
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
