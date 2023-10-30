package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.models.Restaurant;
import ru.liga.services.KitchenService;
@RestController
@RequiredArgsConstructor
public class KithcenController {

    private final KitchenService kitchenService;
//    // Create
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = kitchenService.createRestaurant(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }
//
//    // Read (Retrieve) all
//    @GetMapping
//    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
//        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
//        return ResponseEntity.ok(restaurants);
//    }
//
//    // Read (Retrieve) by ID
//    @GetMapping("/{restaurantId}")
//    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
//        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
//        return ResponseEntity.ok(restaurant);
//    }
//
//    // Update
//    @PutMapping("/{restaurantId}")
//    public ResponseEntity<Restaurant> updateRestaurant(
//            @PathVariable Long restaurantId,
//            @RequestBody Restaurant updatedRestaurant
//    ) {
//        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
//        return ResponseEntity.ok(restaurant);
//    }
//
//    // Delete
//    @DeleteMapping("/{restaurantId}")
//    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
//        restaurantService.deleteRestaurant(restaurantId);
//        return ResponseEntity.noContent().build();
//    }
}
