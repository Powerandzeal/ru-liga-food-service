package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestourantMenuItemDTO;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.KitchenService;
import ru.liga.services.MenuItemsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kitchen")
public class KithcenController {

    private final KitchenService kitchenService;
    private final MenuItemsService menuItemsService;
//    // Create
    @PostMapping("/createRestaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = kitchenService.createRestaurant(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    @PostMapping("/createMenuItems{id}")
    public ResponseEntity<RestaurantMenuItem> createMenuItems(@RequestBody RestourantMenuItemDTO restaurantMenuItem
            ,@RequestParam Long id) {
        ;
        return ResponseEntity.ok(menuItemsService.createMenuItem(id,restaurantMenuItem));
    }
    @DeleteMapping("/deleteMenuItems")
    public ResponseEntity<String> deleteMenuItems(@RequestParam Long id) {
        menuItemsService.deleteMenuItem(id);
        return ResponseEntity.ok("Позиция была удалена из меню");
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
        Restaurant restaurant = kitchenService.getRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }


    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody Restaurant updatedRestaurant
    ) {
        Restaurant restaurant = kitchenService.updateRestaurant(restaurantId, updatedRestaurant);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        kitchenService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
