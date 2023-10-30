package ru.liga.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.models.Restaurant;
import ru.liga.repositories.RestaurantRepository;

@Service
@RequiredArgsConstructor
public class KitchenService {
    private final RestaurantRepository restaurantRepository;
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
    public void kitchenIsOpen() {

    }
    public void kitchenIsClose() {

    }
}
